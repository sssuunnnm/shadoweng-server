package com.bremenband.shadoweng.domain.study.service

import com.bremenband.shadoweng.domain.study.dto.CreateEvaluationRequest
import com.bremenband.shadoweng.domain.study.dto.EvaluationResponse
import com.bremenband.shadoweng.domain.study.entity.Evaluation
import com.bremenband.shadoweng.domain.study.repository.EvaluationRepository
import com.bremenband.shadoweng.domain.study.repository.SentenceRepository
import com.bremenband.shadoweng.domain.study.repository.StudySessionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional(readOnly = true)
class EvaluationService(
    private val evaluationRepository: EvaluationRepository,
    private val studySessionRepository: StudySessionRepository,
    private val sentenceRepository: SentenceRepository
) {
    @Transactional
    fun createEvaluation(sessionId: Long, request: CreateEvaluationRequest): EvaluationResponse {
        val session = studySessionRepository.findById(sessionId)
            .orElseThrow { IllegalArgumentException("세션을 찾을 수 없습니다.") }
        val sentence = sentenceRepository.findById(request.sentenceId)
            .orElseThrow { IllegalArgumentException("문장을 찾을 수 없습니다.") }

        // TODO: AI 분석 연동 시 실제 점수로 교체
        val evaluation = evaluationRepository.save(
            Evaluation(
                session = session,
                sentence = sentence,
                userTranscription = request.userTranscription,
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
        return evaluation.toResponse()
    }

    private fun Evaluation.toResponse() = EvaluationResponse(
        evaluationId = id,
        sessionId = session.id,
        sentenceId = sentence.id,
        userTranscription = userTranscription,
        totalScore = totalScore,
        wordAccuracy = wordAccuracy,
        prosodyAndStress = prosodyAndStress,
        wordRhythmScore = wordRhythmScore,
        boundaryToneScore = boundaryToneScore,
        dynamicStressScore = dynamicStressScore,
        speedSimilarity = speedSimilarity,
        pauseSimilarity = pauseSimilarity
    )
}