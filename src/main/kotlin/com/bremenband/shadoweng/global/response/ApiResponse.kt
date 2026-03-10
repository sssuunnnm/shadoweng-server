package com.bremenband.shadoweng.global.response

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null,
    val isSuccess: Boolean
) {
    companion object {
        fun <T> ok(data: T): ApiResponse<T> =
            ApiResponse(
                code = 200,
                message = "요청에 성공하였습니다.",
                data = data,
                isSuccess = true
            )

        fun <T> fail(message: String, code: Int = 400): ApiResponse<T> =
            ApiResponse(
                code = code,
                message = message,
                isSuccess = false
            )
    }
}