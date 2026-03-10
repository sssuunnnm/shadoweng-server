package com.bremenband.shadoweng.domain.bookmark.mapper

import com.bremenband.shadoweng.domain.bookmark.dto.BookmarkResponse
import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark

object BookmarkMapper {
    fun toResponse(bookmark: Bookmark) = BookmarkResponse(
        bookmarkId = bookmark.id,
        sentenceId = bookmark.sentence.id,
        content = bookmark.sentence.content
    )
}