package com.bremenband.shadoweng.domain.auth.service

import com.bremenband.shadoweng.domain.auth.dto.GuestLoginResponse
import com.bremenband.shadoweng.domain.user.entity.User
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import com.bremenband.shadoweng.global.jwt.JwtProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class AuthService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider
) {
    @Transactional
    fun guestLogin(): GuestLoginResponse {
        val guestId = UUID.randomUUID().toString().take(8)
        val user = userRepository.save(
            User(
                email = "guest_$guestId@shadoweng.com",
                nickname = "Guest_$guestId",
                provider = "GUEST",
                providerId = guestId
            )
        )
        val token = jwtProvider.generateToken(user.id)
        return GuestLoginResponse(token = token, userId = user.id, nickname = user.nickname)
    }
}