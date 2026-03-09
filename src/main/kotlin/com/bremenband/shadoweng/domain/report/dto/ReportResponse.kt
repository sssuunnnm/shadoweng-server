package com.bremenband.shadoweng.domain.report.dto

import java.math.BigDecimal

data class ReportResponse(
    val reportId: Long,
    val sessionId: Long,
    val totalScore: BigDecimal,
    val wordAccuracy: BigDecimal,
    val prosodyAndStress: BigDecimal,
    val wordRhythmScore: BigDecimal,
    val boundaryToneScore: BigDecimal,
    val dynamicStressScore: BigDecimal,
    val speedSimilarity: BigDecimal,
    val pauseSimilarity: BigDecimal
)