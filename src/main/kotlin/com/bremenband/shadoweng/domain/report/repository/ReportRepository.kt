package com.bremenband.shadoweng.domain.report.repository

import com.bremenband.shadoweng.domain.report.entity.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime
import org.springframework.data.repository.query.Param

@Repository
interface ReportRepository : JpaRepository<Report, Long> {
    fun findBySessionId(sessionId: Long): Optional<Report>

    @Query("SELECT r FROM Report r WHERE r.session.user.id = :userId AND r.createdAt >= :from")
    fun findByUserIdAndCreatedAtAfter(@Param("userId") userId: Long, @Param("from") from: LocalDateTime): List<Report>
}


