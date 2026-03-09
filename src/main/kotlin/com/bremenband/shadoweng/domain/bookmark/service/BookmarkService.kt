package com.bremenband.shadoweng.domain.bookmark.service

import com.bremenband.shadoweng.domain.bookmark.dto.BookmarkResponse
import com.bremenband.shadoweng.domain.bookmark.entity.Bookmark
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
        bookmarkRepository.findAllByUserId(userId).map { it.toResponse() }

    @Transactional
    fun toggleBookmark(userId: Long, sentenceId: Long): BookmarkResponse {
        val existing = bookmarkRepository.findByUserIdAndSentenceId(userId, sentenceId)

        // 있으면 삭제, 없으면 생성
        if (existing != null) {
            bookmarkRepository.delete(existing)
            return BookmarkResponse(bookmarkId = existing.id, sentenceId = sentenceId, content = existing.sentence.content)
        }

        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다.") }
        val sentence = sentenceRepository.findById(sentenceId)
            .orElseThrow { IllegalArgumentException("문장을 찾을 수 없습니다.") }

        val bookmark = bookmarkRepository.save(Bookmark(user = user, sentence = sentence))
        return bookmark.toResponse()
    }

    private fun Bookmark.toResponse() = BookmarkResponse(
        bookmarkId = id,
        sentenceId = sentence.id,
        content = sentence.content
    )
}