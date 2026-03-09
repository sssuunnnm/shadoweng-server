package com.bremenband.shadoweng.domain.report.entity

import com.bremenband.shadoweng.domain.study.entity.Sentence
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "weak_sentences")
class WeakSentence(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    val report: Report,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentence_id", nullable = false)
    val sentence: Sentence,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)