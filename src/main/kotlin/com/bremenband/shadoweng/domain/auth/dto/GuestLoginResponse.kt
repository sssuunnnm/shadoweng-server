package com.bremenband.shadoweng.domain.auth.dto

data class GuestLoginResponse(
    val token: String,
    val userId: Long,
    val nickname: String
)