package com.bremenband.shadoweng.domain.user.controller

import com.bremenband.shadoweng.domain.user.service.UserService
import com.bremenband.shadoweng.global.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/app/users")
class UserController(private val userService: UserService) {

    @GetMapping("/me")
    fun getMe(request: HttpServletRequest): ApiResponse<Any> {
        val userId = request.getAttribute("userId") as Long
        return ApiResponse.ok(userService.getMe(userId))
    }
}