package com.bremenband.shadoweng.domain.user.dto

data class UserResponse(
    val userId: Long,
    val email: String,
    val nickname: String,
    val visitedCount: Int
)