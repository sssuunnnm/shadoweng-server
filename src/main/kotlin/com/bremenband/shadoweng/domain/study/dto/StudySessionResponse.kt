package com.bremenband.shadoweng.domain.study.dto

data class StudySessionResponse(
    val sessionId: Long,
    val videoData: VideoData,
    val sentencesData: List<SentenceData>
)

data class VideoData(
    val videoId: String,
    val embedUrl: String,
    val title: String,
    val thumbnailUrl: String?,
    val duration: Int,
    val channelTitle: String
)

data class SentenceData(
    val sentenceId: Long,
    val sentence: String,
    val startSec: Double,
    val endSec: Double,
    val durationSec: Double,
    val studyCount: Int
)