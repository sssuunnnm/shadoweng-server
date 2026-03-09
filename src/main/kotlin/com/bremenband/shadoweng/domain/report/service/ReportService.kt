package com.bremenband.shadoweng.domain.report.service

import com.bremenband.shadoweng.domain.report.dto.DailyReportResponse
import com.bremenband.shadoweng.domain.report.dto.ReportResponse
import com.bremenband.shadoweng.domain.report.entity.Report
import com.bremenband.shadoweng.domain.report.repository.ReportRepository
import com.bremenband.shadoweng.domain.study.repository.EvaluationRepository
import com.bremenband.shadoweng.domain.study.repository.StudySessionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class ReportService(
    private val reportRepository: ReportRepository,
    private val studySessionRepository: StudySessionRepository,
    private val evaluationRepository: EvaluationRepository
) {
    @Transactional
    fun createReport(sessionId: Long): ReportResponse {
        val session = studySessionRepository.findById(sessionId)
            .orElseThrow { IllegalArgumentException("세션을 찾을 수 없습니다.") }

        // 이미 레포트 있으면 그대로 반환
        val existing = reportRepository.findBySessionId(sessionId)
        if (existing.isPresent) return existing.get().toResponse()

        val evaluations = evaluationRepository.findAllBySessionId(sessionId)
        if (evaluations.isEmpty()) throw IllegalArgumentException("평가 데이터가 없습니다.")

        // 평균 계산
        fun avg(selector: (com.bremenband.shadoweng.domain.study.entity.Evaluation) -> BigDecimal?): BigDecimal {
            return evaluations.mapNotNull { selector(it) }
                .takeIf { it.isNotEmpty() }
                ?.let { list -> list.reduce { a, b -> a + b }.divide(BigDecimal(list.size), 2, RoundingMode.HALF_UP) }
                ?: BigDecimal.ZERO
        }

        val report = reportRepository.save(
            Report(
                session = session,
                totalScore = avg { it.totalScore },
                wordAccuracy = avg { it.wordAccuracy },
                prosodyAndStress = avg { it.prosodyAndStress },
                wordRhythmScore = avg { it.wordRhythmScore },
                boundaryToneScore = avg { it.boundaryToneScore },
                dynamicStressScore = avg { it.dynamicStressScore },
                speedSimilarity = avg { it.speedSimilarity },
                pauseSimilarity = avg { it.pauseSimilarity }
            )
        )
        return report.toResponse()
    }

    fun getReport(sessionId: Long): ReportResponse =
        reportRepository.findBySessionId(sessionId)
            .orElseThrow { IllegalArgumentException("레포트가 없습니다.") }
            .toResponse()


    private fun Report.toResponse() = ReportResponse(
        reportId = id,
        sessionId = session.id,
        totalScore = totalScore,
        wordAccuracy = wordAccuracy,
        prosodyAndStress = prosodyAndStress,
        wordRhythmScore = wordRhythmScore,
        boundaryToneScore = boundaryToneScore,
        dynamicStressScore = dynamicStressScore,
        speedSimilarity = speedSimilarity,
        pauseSimilarity = pauseSimilarity
    )

    fun getDailyReport(userId: Long): DailyReportResponse {
        val today = LocalDate.now()
        val from = today.atStartOfDay()
        val reports = reportRepository.findByUserIdAndCreatedAtAfter(userId, from)

        val averageScore = if (reports.isEmpty()) BigDecimal.ZERO
        else reports.map { it.totalScore }
            .reduce { a, b -> a + b }
            .divide(BigDecimal(reports.size), 2, RoundingMode.HALF_UP)

        // 스트릭: 최근 7일 학습 여부
        val weeklyStatus = (6 downTo 0).map { daysAgo ->
            val dayStart = today.minusDays(daysAgo.toLong()).atStartOfDay()
            val dayEnd = dayStart.plusDays(1)
            reportRepository.findByUserIdAndCreatedAtAfter(userId, dayStart)
                .any { it.createdAt < dayEnd }
        }

        return DailyReportResponse(
            totalSentenceCount = reports.sumOf { 3 },
            completedSentenceCount = reports.size * 3,
            averageScore = averageScore,
            streakDays = weeklyStatus.reversed().takeWhile { it }.size,
            weeklyStatus = weeklyStatus
        )
    }
}