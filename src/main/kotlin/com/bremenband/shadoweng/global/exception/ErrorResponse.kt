package com.bremenband.shadoweng.global.exception

data class ErrorResponse(
    val code: Int,
    val message: String,
    val isSuccess: Boolean = false
) {
    companion object {
        fun of(errorCode: ErrorCode) = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message
        )
    }
}