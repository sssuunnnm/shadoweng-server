package com.bremenband.shadoweng.domain.video.dto

data class VideoResponse(
    val videoId: String,
    val title: String,
    val thumbnailUrl: String?,
    val duration: Int,
    val channelTitle: String,
    val embedUrl: String
)