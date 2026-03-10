package com.bremenband.shadoweng.domain.video.mapper

import com.bremenband.shadoweng.domain.video.dto.VideoResponse
import com.bremenband.shadoweng.domain.video.entity.Video

object VideoMapper {
    fun toResponse(video: Video) = VideoResponse(
        videoId = video.videoId,
        title = video.title,
        thumbnailUrl = video.thumbnailUrl,
        duration = video.duration,
        channelTitle = video.channelTitle,
        embedUrl = video.embedUrl
    )
}