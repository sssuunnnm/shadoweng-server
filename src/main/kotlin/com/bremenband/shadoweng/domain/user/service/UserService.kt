package com.bremenband.shadoweng.domain.user.service

import com.bremenband.shadoweng.domain.user.dto.UserResponse
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {

    fun getMe(userId: Long): UserResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다.") }
        return UserResponse(
            userId = user.id,
            email = user.email,
            nickname = user.nickname,
            visitedCount = user.visitedCount
        )
    }
}