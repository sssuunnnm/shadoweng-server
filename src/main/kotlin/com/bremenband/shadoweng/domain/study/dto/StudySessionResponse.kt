package com.bremenband.shadoweng.domain.study.dto

data class StudySessionResponse(
    val sessionId: Long,
    val videoId: String,
    val title: String,
    val thumbnailUrl: String?,
    val startSec: Int,
    val endSec: Int,
    val progressRate: Int,
    val status: String
)