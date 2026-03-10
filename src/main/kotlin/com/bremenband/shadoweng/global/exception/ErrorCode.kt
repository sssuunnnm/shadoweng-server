package com.bremenband.shadoweng.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: Int,
    val message: String
) {
    // 공통
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 400, "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 403, "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 오류가 발생했습니다."),

    // 유저
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 1001, "사용자를 찾을 수 없습니다."),

    // 세션
    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, 2001, "세션을 찾을 수 없습니다."),

    // 레포트
    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, 3001, "레포트를 찾을 수 없습니다."),
    EVALUATION_NOT_FOUND(HttpStatus.NOT_FOUND, 3002, "평가 데이터가 없습니다."),

    // 북마크
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, 4001, "북마크를 찾을 수 없습니다."),

    // 영상
    VIDEO_INVALID_URL(HttpStatus.BAD_REQUEST, 5001, "유효하지 않은 유튜브 URL입니다."),
    SENTENCE_NOT_FOUND(HttpStatus.NOT_FOUND, 5002, "문장을 찾을 수 없습니다.")
}