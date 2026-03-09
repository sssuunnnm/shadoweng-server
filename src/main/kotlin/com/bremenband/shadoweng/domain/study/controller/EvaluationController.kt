package com.bremenband.shadoweng.domain.study.controller

import com.bremenband.shadoweng.domain.study.dto.CreateEvaluationRequest
import com.bremenband.shadoweng.domain.study.service.EvaluationService
import com.bremenband.shadoweng.global.response.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/app/study-sessions/{sessionId}")
class EvaluationController(private val evaluationService: EvaluationService) {

    @PostMapping("/evaluations")
    fun createEvaluation(
        @PathVariable sessionId: Long,
        @RequestBody request: CreateEvaluationRequest
    ): ApiResponse<Any> =
        ApiResponse.ok(evaluationService.createEvaluation(sessionId, request))
}