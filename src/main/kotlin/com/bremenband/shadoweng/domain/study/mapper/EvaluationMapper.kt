package com.bremenband.shadoweng.domain.study.mapper

import com.bremenband.shadoweng.domain.study.dto.EvaluationResponse
import com.bremenband.shadoweng.domain.study.entity.Evaluation

object EvaluationMapper {
    fun toResponse(evaluation: Evaluation) = EvaluationResponse(
        evaluationId = evaluation.id,
        sessionId = evaluation.session.id,
        sentenceId = evaluation.sentence.id,
        userTranscription = evaluation.userTranscription,
        totalScore = evaluation.totalScore,
        wordAccuracy = evaluation.wordAccuracy,
        prosodyAndStress = evaluation.prosodyAndStress,
        wordRhythmScore = evaluation.wordRhythmScore,
        boundaryToneScore = evaluation.boundaryToneScore,
        dynamicStressScore = evaluation.dynamicStressScore,
        speedSimilarity = evaluation.speedSimilarity,
        pauseSimilarity = evaluation.pauseSimilarity
    )
}