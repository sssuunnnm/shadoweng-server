package com.bremenband.shadoweng.domain.video.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "videos")
class Video(

    @Id
    @Column(name = "video_id", length = 50)
    val videoId: String,

    @Column(nullable = false, length = 255)
    val title: String,

    @Column(name = "embed_url", nullable = false, length = 255)
    val embedUrl: String,

    @Column(name = "thumbnail_url", length = 255)
    val thumbnailUrl: String? = null,

    @Column(nullable = false)
    val duration: Int,

    @Column(name = "channel_title", length = 255)
    val channelTitle: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)