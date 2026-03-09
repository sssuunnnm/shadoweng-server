package com.bremenband.shadoweng.domain.report.controller

import com.bremenband.shadoweng.domain.report.service.ReportService
import com.bremenband.shadoweng.global.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/app")
class ReportController(private val reportService: ReportService) {

    @PostMapping("/study-sessions/{sessionId}/reports")
    fun createReport(@PathVariable sessionId: Long): ApiResponse<Any> =
        ApiResponse.ok(reportService.createReport(sessionId))

    @GetMapping("/study-sessions/{sessionId}/reports")
    fun getReport(@PathVariable sessionId: Long): ApiResponse<Any> =
        ApiResponse.ok(reportService.getReport(sessionId))

    @GetMapping("/reports/daily")
    fun getDailyReport(request: HttpServletRequest): ApiResponse<Any> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(reportService.getDailyReport(userId))
    }
}