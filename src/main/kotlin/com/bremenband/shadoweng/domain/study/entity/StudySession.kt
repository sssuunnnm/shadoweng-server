package com.bremenband.shadoweng.domain.study.entity

import com.bremenband.shadoweng.domain.user.entity.User
import com.bremenband.shadoweng.domain.video.entity.Video
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "study_sessions")
class StudySession(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    val video: Video,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "start_sec", nullable = false)
    val startSec: Double,

    @Column(name = "end_sec", nullable = false)
    val endSec: Double,

    @Column(name = "progress_rate", nullable = false)
    val progressRate: Int = 0,

    @Column(nullable = false, length = 20)
    var status: String = "ACTIVE",

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),


)