package com.bremenband.shadoweng.domain.bookmark.dto

data class BookmarkResponse(
    val bookmarkId: Long,
    val sentenceId: Long,
    val content: String
)