package com.bremenband.shadoweng.global.config

import com.bremenband.shadoweng.domain.study.entity.Sentence
import com.bremenband.shadoweng.domain.study.repository.SentenceRepository
import com.bremenband.shadoweng.domain.study.repository.StudySessionRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark
import com.bremenband.shadoweng.domain.bookmark.repository.BookmarkRepository
import com.bremenband.shadoweng.domain.report.entity.Report
import com.bremenband.shadoweng.domain.report.repository.ReportRepository
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import java.math.BigDecimal

@Component
class DataInitializer(
    private val studySessionRepository: StudySessionRepository,
    private val sentenceRepository: SentenceRepository,
    private val userRepository: UserRepository,
    private val reportRepository: ReportRepository,
    private val bookmarkRepository: BookmarkRepository
) : ApplicationRunner {

    @Transactional
    override fun run(args: ApplicationArguments) {
        val sessions = studySessionRepository.findAll()
        println("세션 수: ${sessions.size}")
        sessions.forEach { session ->
            val existing = sentenceRepository.findAllBySessionId(session.id)
            if (existing.isEmpty()) {
                listOf(
                    Sentence(session = session, content = "I had this meeting with a big studio Hollywood casting director.", startSec = 3, endSec = 7, durationSec = 4),
                    Sentence(session = session, content = "She told me I was very talented.", startSec = 8, endSec = 11, durationSec = 3),
                    Sentence(session = session, content = "But I needed to change my name.", startSec = 12, endSec = 15, durationSec = 3),
                ).forEach { sentenceRepository.save(it) }
                println("세션 ${session.id} 문장 생성 완료")
            }

            // 레포트 Mock
            if (reportRepository.findBySessionId(session.id).isEmpty) {
                reportRepository.save(
                    Report(
                        session = session,
                        totalScore = BigDecimal("85.00"),
                        wordAccuracy = BigDecimal("90.00"),
                        prosodyAndStress = BigDecimal("80.00"),
                        wordRhythmScore = BigDecimal("85.00"),
                        boundaryToneScore = BigDecimal("82.00"),
                        dynamicStressScore = BigDecimal("88.00"),
                        speedSimilarity = BigDecimal("83.00"),
                        pauseSimilarity = BigDecimal("87.00")
                    )
                )
                println("세션 ${session.id} 레포트 생성 완료")
            }
        }

        // 북마크 Mock (첫 번째 유저 + 첫 번째 문장)
        val user = userRepository.findAll().firstOrNull() ?: return
        val sentence = sentenceRepository.findAll().firstOrNull() ?: return
        if (bookmarkRepository.findByUserIdAndSentenceId(user.id, sentence.id) == null) {
            bookmarkRepository.save(Bookmark(user = user, sentence = sentence))
            println("북마크 Mock 생성 완료")
        }
    }
}