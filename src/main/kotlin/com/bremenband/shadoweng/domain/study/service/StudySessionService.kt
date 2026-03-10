package com.bremenband.shadoweng.domain.study.service

import com.bremenband.shadoweng.domain.study.dto.CreateStudySessionRequest
import com.bremenband.shadoweng.domain.study.dto.StudySessionResponse
import com.bremenband.shadoweng.domain.study.entity.StudySession
import com.bremenband.shadoweng.domain.study.mapper.StudySessionMapper
import com.bremenband.shadoweng.domain.study.repository.StudySessionRepository
import com.bremenband.shadoweng.domain.user.repository.UserRepository
import com.bremenband.shadoweng.domain.study.dto.ActiveSessionListResponse
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
        return StudySessionMapper.toResponse(session)
    }

    fun getSessions(userId: Long): ActiveSessionListResponse =
        ActiveSessionListResponse(
            ActiveSessions = studySessionRepository.findAllByUserId(userId)
                .map { StudySessionMapper.toActiveSessionResponse(it) }
        )

    fun getSession(sessionId: Long): StudySessionResponse =
        StudySessionMapper.toResponse(
            studySessionRepository.findById(sessionId)
                .orElseThrow { IllegalArgumentException("세션을 찾을 수 없습니다.") }
        )

    fun getRecentSession(userId: Long): StudySessionResponse? =
        studySessionRepository.findTopByUserIdOrderByCreatedAtDesc(userId)
            .orElse(null)
            ?.let { StudySessionMapper.toResponse(it) }
}