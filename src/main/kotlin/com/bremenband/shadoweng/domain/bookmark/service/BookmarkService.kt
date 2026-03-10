package com.bremenband.shadoweng.domain.bookmark.service

import com.bremenband.shadoweng.domain.bookmark.dto.BookmarkResponse
import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark
import com.bremenband.shadoweng.domain.bookmark.mapper.BookmarkMapper
import com.bremenband.shadoweng.domain.bookmark.repository.BookmarkRepository
import com.bremenband.shadoweng.domain.study.repository.SentenceRepository
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookmarkService(
    private val bookmarkRepository: BookmarkRepository,
    private val sentenceRepository: SentenceRepository,
    private val userRepository: UserRepository
) {
    fun getBookmarks(userId: Long): List<BookmarkResponse> =
        bookmarkRepository.findAllByUserId(userId).map { BookmarkMapper.toResponse(it) }

    @Transactional
    fun toggleBookmark(userId: Long, sentenceId: Long): BookmarkResponse {
        val existing = bookmarkRepository.findByUserIdAndSentenceId(userId, sentenceId)

        if (existing != null) {
            val response = BookmarkMapper.toResponse(existing)
            bookmarkRepository.delete(existing)
            return response
        }

        val user = userRepository.getReferenceById(userId)
        val sentence = sentenceRepository.findById(sentenceId)
            .orElseThrow { IllegalArgumentException("문장을 찾을 수 없습니다.") }

        val bookmark = bookmarkRepository.save(Bookmark(user = user, sentence = sentence))
        return BookmarkMapper.toResponse(bookmark)
    }
}