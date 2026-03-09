package com.bremenband.shadoweng.domain.study.repository

import com.bremenband.shadoweng.domain.study.entity.StudySession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface StudySessionRepository : JpaRepository<StudySession, Long> {
    fun findAllByUserId(userId: Long): List<StudySession>
    fun findTopByUserIdOrderByCreatedAtDesc(userId: Long): Optional<StudySession>
}