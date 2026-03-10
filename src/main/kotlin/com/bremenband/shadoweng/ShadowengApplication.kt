package com.bremenband.shadoweng

import com.bremenband.shadoweng.global.config.JwtConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig::class)
class ShadowengApplication

fun main(args: Array<String>) {
    runApplication<ShadowengApplication>(*args)
}