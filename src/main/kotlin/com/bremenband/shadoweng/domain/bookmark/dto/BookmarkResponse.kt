package com.bremenband.shadoweng.domain.bookmark.dto

data class BookmarkResponse(
    val sentenceId: Long,
    val sentence: String,
    val sessionId: Long
)