package com.bremenband.shadoweng.global.jwt

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JwtInterceptor(private val jwtProvider: JwtProvider) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization")?.removePrefix("Bearer ") ?: run {
            response.sendError(401, "토큰이 없습니다.")
            return false
        }
        if (!jwtProvider.validate(token)) {
            response.sendError(401, "유효하지 않은 토큰입니다.")
            return false
        }
        request.setAttribute("userId", jwtProvider.getUserId(token))
        return true
    }
}