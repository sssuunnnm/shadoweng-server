package com.bremenband.shadoweng.domain.report.entity

import com.bremenband.shadoweng.domain.study.entity.StudySession
import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "reports")
class Report(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    val session: StudySession,

    @Column(name = "total_score", nullable = false, precision = 5, scale = 2)
    val totalScore: BigDecimal,

    @Column(name = "word_accuracy", nullable = false, precision = 5, scale = 2)
    val wordAccuracy: BigDecimal,

    @Column(name = "prosody_and_stress", nullable = false, precision = 5, scale = 2)
    val prosodyAndStress: BigDecimal,

    @Column(name = "word_rhythm_score", nullable = false, precision = 5, scale = 2)
    val wordRhythmScore: BigDecimal,

    @Column(name = "boundary_tone_score", nullable = false, precision = 5, scale = 2)
    val boundaryToneScore: BigDecimal,

    @Column(name = "dynamic_stress_score", nullable = false, precision = 5, scale = 2)
    val dynamicStressScore: BigDecimal,

    @Column(name = "speed_similarity", nullable = false, precision = 5, scale = 2)
    val speedSimilarity: BigDecimal,

    @Column(name = "pause_similarity", nullable = false, precision = 5, scale = 2)
    val pauseSimilarity: BigDecimal,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

)