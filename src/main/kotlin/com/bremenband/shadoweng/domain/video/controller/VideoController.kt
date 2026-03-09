package com.bremenband.shadoweng.domain.video.controller

import com.bremenband.shadoweng.domain.video.service.VideoService
import com.bremenband.shadoweng.global.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/app")
class VideoController(private val videoService: VideoService) {

    @GetMapping("/youtube")
    fun getVideo(@RequestParam url: String): ApiResponse<Any> =
        ApiResponse.ok(videoService.getOrCreateVideo(url))
}