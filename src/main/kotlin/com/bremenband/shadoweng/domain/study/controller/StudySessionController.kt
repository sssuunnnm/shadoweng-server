package com.bremenband.shadoweng.domain.study.controller

import com.bremenband.shadoweng.domain.report.dto.ReportResponse
import com.bremenband.shadoweng.domain.report.service.ReportService
import com.bremenband.shadoweng.domain.study.dto.ActiveSessionListResponse
import com.bremenband.shadoweng.domain.study.dto.CreateEvaluationRequest
import com.bremenband.shadoweng.domain.study.dto.CreateStudySessionRequest
import com.bremenband.shadoweng.domain.study.dto.EvaluationResponse
import com.bremenband.shadoweng.domain.study.dto.StudySessionResponse
import com.bremenband.shadoweng.domain.study.service.EvaluationService
import com.bremenband.shadoweng.domain.study.service.StudySessionService
import com.bremenband.shadoweng.global.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/app/study-sessions")
class StudySessionController(
    private val studySessionService: StudySessionService,
    private val reportService: ReportService,
    private val evaluationService: EvaluationService
) {

    @PostMapping
    fun createSession(
        request: HttpServletRequest,
        @RequestBody body: CreateStudySessionRequest
    ): ApiResponse<StudySessionResponse> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(studySessionService.createSession(userId, body))
    }

    @GetMapping
    fun getSessions(
        request: HttpServletRequest
    ): ApiResponse<ActiveSessionListResponse> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(studySessionService.getSessions(userId))
    }

    @GetMapping("/recent")
    fun getRecentSession(
        request: HttpServletRequest
    ): ApiResponse<StudySessionResponse?> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(studySessionService.getRecentSession(userId))
    }

    @GetMapping("/{sessionId}")
    fun getSession(
        @PathVariable sessionId: Long
    ): ApiResponse<StudySessionResponse> =
        ApiResponse.ok(studySessionService.getSession(sessionId))

    @PostMapping("/{sessionId}/reports")
    fun createReport(
        @PathVariable sessionId: Long
    ): ApiResponse<ReportResponse> =
        ApiResponse.ok(reportService.createReport(sessionId))

    @GetMapping("/{sessionId}/reports")
    fun getReport(
        @PathVariable sessionId: Long
    ): ApiResponse<ReportResponse> =
        ApiResponse.ok(reportService.getReport(sessionId))

    @PostMapping("/{sessionId}/evaluations")
    fun createEvaluation(
        @PathVariable sessionId: Long,
        @RequestBody request: CreateEvaluationRequest
    ): ApiResponse<EvaluationResponse> =
        ApiResponse.ok(evaluationService.createEvaluation(sessionId, request))
}