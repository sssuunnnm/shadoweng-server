package com.bremenband.shadoweng.global.exception

class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message)