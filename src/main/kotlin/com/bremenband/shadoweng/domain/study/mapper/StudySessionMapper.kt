package com.bremenband.shadoweng.domain.study.mapper

import com.bremenband.shadoweng.domain.study.dto.*
import com.bremenband.shadoweng.domain.study.entity.Sentence
import com.bremenband.shadoweng.domain.study.entity.StudySession

object StudySessionMapper {
    fun toResponse(session: StudySession, sentences: List<Sentence>) = StudySessionResponse(
        sessionId = session.id,
        videoData = VideoData(
            videoId = session.video.videoId,
            embedUrl = session.video.embedUrl,
            title = session.video.title,
            thumbnailUrl = session.video.thumbnailUrl,
            duration = session.video.duration,
            channelTitle = session.video.channelTitle
        ),
        sentencesData = sentences.map { s ->
            SentenceData(
                sentenceId = s.id,
                sentence = s.content,
                startSec = s.startSec.toDouble(),
                endSec = s.endSec.toDouble(),
                durationSec = s.durationSec.toDouble(),
                studyCount = s.studyCount
            )
        }
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