package com.bremenband.shadoweng.domain.video.service

import com.bremenband.shadoweng.domain.video.dto.VideoResponse
import com.bremenband.shadoweng.domain.video.entity.Video
import com.bremenband.shadoweng.domain.video.repository.VideoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class VideoService(private val videoRepository: VideoRepository) {

    @Transactional
    fun getOrCreateVideo(url: String): VideoResponse {
        val videoId = extractVideoId(url)
            ?: throw IllegalArgumentException("유효하지 않은 유튜브 URL입니다.")

        val video = videoRepository.findById(videoId).orElseGet {
            videoRepository.save(
                Video(
                    videoId = videoId,
                    title = "Mock Title",           // TODO: 유튜브 API 연동 시 교체
                    embedUrl = "https://www.youtube.com/embed/$videoId",
                    thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg",
                    duration = 0,
                    channelTitle = "Mock Channel"   // TODO: 유튜브 API 연동 시 교체
                )
            )
        }

        return VideoResponse(
            videoId = video.videoId,
            title = video.title,
            thumbnailUrl = video.thumbnailUrl,
            duration = video.duration,
            channelTitle = video.channelTitle,
            embedUrl = video.embedUrl
        )
    }

    private fun extractVideoId(url: String): String? {
        val patterns = listOf(
            Regex("(?:youtube\\.com/watch\\?v=|youtu\\.be/|youtube\\.com/embed/)([a-zA-Z0-9_-]{11})"),
        )
        return patterns.firstNotNullOfOrNull { it.find(url)?.groupValues?.get(1) }
    }
}