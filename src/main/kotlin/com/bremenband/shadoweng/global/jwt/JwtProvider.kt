package com.bremenband.shadoweng.global.jwt

import com.bremenband.shadoweng.global.config.JwtConfig
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider(private val jwtConfig: JwtConfig) {

    private val key by lazy {
        Keys.hmacShaKeyFor(jwtConfig.secret.toByteArray())
    }

    fun generateToken(userId: Long): String {
        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtConfig.expiration))
            .signWith(key)
            .compact()
    }

    fun getUserId(token: String): Long {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
            .toLong()
    }

    fun validate(token: String): Boolean = runCatching { getUserId(token) }.isSuccess
}