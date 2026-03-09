package com.bremenband.shadoweng.domain.study.controller

import com.bremenband.shadoweng.domain.study.dto.CreateStudySessionRequest
import com.bremenband.shadoweng.domain.study.service.StudySessionService
import com.bremenband.shadoweng.global.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/app/study-sessions")
class StudySessionController(private val studySessionService: StudySessionService) {

    @PostMapping
    fun createSession(
        request: HttpServletRequest,
        @RequestBody body: CreateStudySessionRequest
    ): ApiResponse<Any> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(studySessionService.createSession(userId, body))
    }

    @GetMapping
    fun getSessions(request: HttpServletRequest): ApiResponse<Any> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(studySessionService.getSessions(userId))
    }

    @GetMapping("/recent")
    fun getRecentSession(request: HttpServletRequest): ApiResponse<Any> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(studySessionService.getRecentSession(userId))
    }

    @GetMapping("/{sessionId}")
    fun getSession(@PathVariable sessionId: Long): ApiResponse<Any> =
        ApiResponse.ok(studySessionService.getSession(sessionId))
}