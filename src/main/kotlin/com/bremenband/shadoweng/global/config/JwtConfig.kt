package com.bremenband.shadoweng.global.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtConfig {
    var secret: String = ""
    var expiration: Long = 86400000 // 24시간
}