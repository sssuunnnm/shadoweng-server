package com.bremenband.shadoweng.domain.study.dto

data class CreateStudySessionRequest(
    val embedUrl: String,
    val startSec: Double,
    val endSec: Double
)