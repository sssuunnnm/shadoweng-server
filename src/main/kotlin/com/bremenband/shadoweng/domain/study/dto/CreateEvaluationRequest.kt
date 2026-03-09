package com.bremenband.shadoweng.domain.study.dto

data class CreateEvaluationRequest(
    val sentenceId: Long,
    val userTranscription: String?
)