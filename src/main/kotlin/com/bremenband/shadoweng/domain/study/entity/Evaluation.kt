package com.bremenband.shadoweng.domain.study.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "evaluations")
class Evaluation(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    val session: StudySession,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentence_id", nullable = false)
    val sentence: Sentence,

    @Column(name = "user_transcription", columnDefinition = "TEXT")
    val userTranscription: String? = null,

    @Column(name = "word_level_feedback", columnDefinition = "JSON")
    val wordLevelFeedback: String? = null,

    @Column(name = "boundary_tone_feedback", columnDefinition = "JSON")
    val boundaryToneFeedback: String? = null,

    @Column(name = "dynamic_stress_feedback", columnDefinition = "JSON")
    val dynamicStressFeedback: String? = null,

    @Column(name = "total_score", precision = 5, scale = 2)
    val totalScore: BigDecimal? = null,

    @Column(name = "word_accuracy", precision = 5, scale = 2)
    val wordAccuracy: BigDecimal? = null,

    @Column(name = "prosody_and_stress", precision = 5, scale = 2)
    val prosodyAndStress: BigDecimal? = null,

    @Column(name = "word_rhythm_score", precision = 5, scale = 2)
    val wordRhythmScore: BigDecimal? = null,

    @Column(name = "boundary_tone_score", precision = 5, scale = 2)
    val boundaryToneScore: BigDecimal? = null,

    @Column(name = "dynamic_stress_score", precision = 5, scale = 2)
    val dynamicStressScore: BigDecimal? = null,

    @Column(name = "speed_similarity", precision = 5, scale = 2)
    val speedSimilarity: BigDecimal? = null,

    @Column(name = "pause_similarity", precision = 5, scale = 2)
    val pauseSimilarity: BigDecimal? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)