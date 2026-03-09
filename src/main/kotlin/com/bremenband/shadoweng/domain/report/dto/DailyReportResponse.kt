package com.bremenband.shadoweng.domain.report.dto

import java.math.BigDecimal

data class DailyReportResponse(
    val totalSentenceCount: Int,
    val completedSentenceCount: Int,
    val averageScore: BigDecimal,
    val streakDays: Int,
    val weeklyStatus: List<Boolean>
)