package com.bremenband.shadoweng.domain.study.repository

import com.bremenband.shadoweng.domain.study.entity.Evaluation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluationRepository : JpaRepository<Evaluation, Long> {
    fun findAllBySessionId(sessionId: Long): List<Evaluation>
    fun findBySentenceId(sentenceId: Long): List<Evaluation>
}