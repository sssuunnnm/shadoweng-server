package com.bremenband.shadoweng.domain.study.dto

data class ActiveSessionResponse(
    val sessionId: Long,
    val thumbnails: ThumbnailResponse,
    val progressRate: Int
)