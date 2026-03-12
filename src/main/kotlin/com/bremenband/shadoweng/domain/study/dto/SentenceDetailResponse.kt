package com.bremenband.shadoweng.domain.study.dto

data class SentenceDetailResponse(
    val step: Int,
    val sessionId: Long,
    val sentenceId: Long,
    val sentence: String,
    val hiddenSentence: String?,
    val startSec: Double,
    val endSec: Double,
    val durationSec: Double,
    val studyCount: Int
)