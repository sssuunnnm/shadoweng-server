package com.bremenband.shadoweng.domain.study.service

import com.bremenband.shadoweng.domain.study.dto.CreateStudySessionRequest
import com.bremenband.shadoweng.domain.study.dto.StudySessionResponse
import com.bremenband.shadoweng.domain.study.entity.StudySession
import com.bremenband.shadoweng.domain.study.repository.StudySessionRepository
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import com.bremenband.shadoweng.domain.video.repository.VideoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StudySessionService(
    private val studySessionRepository: StudySessionRepository,
    private val userRepository: UserRepository,
    private val videoRepository: VideoRepository
) {
    @Transactional
    fun createSession(userId: Long, request: CreateStudySessionRequest): StudySessionResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다.") }
        val video = videoRepository.findById(request.videoId)
            .orElseThrow { IllegalArgumentException("영상을 찾을 수 없습니다.") }

        val session = studySessionRepository.save(
            StudySession(
                user = user,
                video = video,
                startSec = request.startSec,
                endSec = request.endSec
            )
        )
        return session.toResponse()
    }

    fun getSessions(userId: Long): List<StudySessionResponse> =
        studySessionRepository.findAllByUserId(userId).map { it.toResponse() }

    fun getSession(sessionId: Long): StudySessionResponse =
        studySessionRepository.findById(sessionId)
            .orElseThrow { IllegalArgumentException("세션을 찾을 수 없습니다.") }
            .toResponse()

    fun getRecentSession(userId: Long): StudySessionResponse =
        studySessionRepository.findTopByUserIdOrderByCreatedAtDesc(userId)
            .orElseThrow { IllegalArgumentException("세션이 없습니다.") }
            .toResponse()

    private fun StudySession.toResponse() = StudySessionResponse(
        sessionId = id,
        videoId = video.videoId,
        title = video.title,
        thumbnailUrl = video.thumbnailUrl,
        startSec = startSec,
        endSec = endSec,
        progressRate = progressRate,
        status = status
    )
}