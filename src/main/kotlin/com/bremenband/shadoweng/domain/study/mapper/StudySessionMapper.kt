package com.bremenband.shadoweng.domain.study.mapper

import com.bremenband.shadoweng.domain.study.dto.StudySessionResponse
import com.bremenband.shadoweng.domain.study.entity.StudySession
import com.bremenband.shadoweng.domain.study.dto.ActiveSessionResponse
import com.bremenband.shadoweng.domain.study.dto.ThumbnailResponse


object StudySessionMapper {
    fun toResponse(session: StudySession) = StudySessionResponse(
        sessionId = session.id,
        videoId = session.video.videoId,
        title = session.video.title,
        thumbnailUrl = session.video.thumbnailUrl,
        startSec = session.startSec,
        endSec = session.endSec,
        progressRate = session.progressRate,
        status = session.status
    )

    fun toActiveSessionResponse(session: StudySession) = ActiveSessionResponse(
        sessionId = session.id,
        thumbnails = ThumbnailResponse(
            url = session.video.thumbnailUrl ?: "",
            width = 640,
            height = 480
        ),
        progressRate = session.progressRate
    )
}