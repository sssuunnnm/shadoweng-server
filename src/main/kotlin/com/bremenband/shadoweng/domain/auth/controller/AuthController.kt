package com.bremenband.shadoweng.domain.auth.controller

import com.bremenband.shadoweng.domain.auth.service.AuthService
import com.bremenband.shadoweng.global.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/app/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login/guest")
    fun guestLogin(): ApiResponse<Any> =
        ApiResponse.ok(authService.guestLogin())
}