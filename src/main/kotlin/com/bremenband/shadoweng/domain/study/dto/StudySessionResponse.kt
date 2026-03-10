package com.bremenband.shadoweng.domain.study.dto

data class StudySessionResponse(
    val sessionId: Long,
    val videoId: String,
    val title: String,
    val thumbnailUrl: String?,
    val startSec: Double,
    val endSec: Double,
    val progressRate: Int,
    val status: String
)