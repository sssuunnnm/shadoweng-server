package com.bremenband.shadoweng.domain.study.mapper

import com.bremenband.shadoweng.domain.study.dto.StudySessionResponse
import com.bremenband.shadoweng.domain.study.entity.StudySession

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
}