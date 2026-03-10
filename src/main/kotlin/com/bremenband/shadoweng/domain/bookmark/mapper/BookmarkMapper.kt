package com.bremenband.shadoweng.domain.bookmark.mapper

import com.bremenband.shadoweng.domain.bookmark.dto.BookmarkResponse
import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark

object BookmarkMapper {
    fun toResponse(bookmark: Bookmark) = BookmarkResponse(
        sentenceId = bookmark.sentence.id,
        sentence = bookmark.sentence.content,
        sessionId = bookmark.sentence.session.id
    )
}