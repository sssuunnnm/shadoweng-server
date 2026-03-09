package com.bremenband.shadoweng.domain.study.repository

import com.bremenband.shadoweng.domain.study.entity.Sentence
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SentenceRepository : JpaRepository<Sentence, Long> {
    fun findAllBySessionId(sessionId: Long): List<Sentence>
}