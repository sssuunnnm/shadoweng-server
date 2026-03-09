package com.bremenband.shadoweng.domain.study.dto

data class CreateStudySessionRequest(
    val videoId: String,
    val startSec: Int,
    val endSec: Int
)