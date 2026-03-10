package com.bremenband.shadoweng.domain.bookmark.controller

import com.bremenband.shadoweng.domain.bookmark.dto.BookmarkResponse
import com.bremenband.shadoweng.domain.bookmark.service.BookmarkService
import com.bremenband.shadoweng.global.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/app")
class BookmarkController(private val bookmarkService: BookmarkService) {

    @GetMapping("/bookmarks")
    fun getBookmarks(
        request: HttpServletRequest
    ): ApiResponse<List<BookmarkResponse>> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(bookmarkService.getBookmarks(userId))
    }

    @PatchMapping("/sentences/{sentenceId}")
    fun toggleBookmark(
        request: HttpServletRequest,
        @PathVariable sentenceId: Long
    ): ApiResponse<BookmarkResponse> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(bookmarkService.toggleBookmark(userId, sentenceId))
    }
}