package com.bremenband.shadoweng.domain.report.controller

import com.bremenband.shadoweng.domain.report.dto.DailyReportResponse
import com.bremenband.shadoweng.domain.report.service.ReportService
import com.bremenband.shadoweng.global.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/app/reports")
class ReportController(private val reportService: ReportService) {

    @GetMapping("/daily")
    fun getDailyReport(
        request: HttpServletRequest
    ): ApiResponse<DailyReportResponse> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(reportService.getDailyReport(userId))
    }
}