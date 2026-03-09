package com.bremenband.shadoweng.domain.study.dto

import java.math.BigDecimal

data class EvaluationResponse(
    val evaluationId: Long,
    val sessionId: Long,
    val sentenceId: Long,
    val userTranscription: String?,
    val totalScore: BigDecimal?,
    val wordAccuracy: BigDecimal?,
    val prosodyAndStress: BigDecimal?,
    val wordRhythmScore: BigDecimal?,
    val boundaryToneScore: BigDecimal?,
    val dynamicStressScore: BigDecimal?,
    val speedSimilarity: BigDecimal?,
    val pauseSimilarity: BigDecimal?
)