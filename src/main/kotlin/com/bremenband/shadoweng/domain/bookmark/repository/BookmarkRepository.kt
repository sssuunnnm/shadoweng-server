package com.bremenband.shadoweng.domain.bookmark.repository

import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookmarkRepository : JpaRepository<Bookmark, Long> {
    fun findAllByUserId(userId: Long): List<Bookmark>
    fun findByUserIdAndSentenceId(userId: Long, sentenceId: Long): Bookmark?
}