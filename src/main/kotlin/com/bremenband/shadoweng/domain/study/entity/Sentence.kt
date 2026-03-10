package com.bremenband.shadoweng.domain.study.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sentences")
class Sentence(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    val session: StudySession,

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String,

    @Column(name = "start_sec", nullable = false)
    val startSec: Int,

    @Column(name = "end_sec", nullable = false)
    val endSec: Int,

    @Column(name = "duration_sec", nullable = false)
    val durationSec: Int,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,
)