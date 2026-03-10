package com.bremenband.shadoweng.domain.user.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 50)
    val email: String,

    @Column(nullable = false, length = 20)
    val nickname: String,

    @Column(nullable = false, length = 20)
    val provider: String,

    @Column(name = "provider_id", nullable = false, length = 20)
    val providerId: String,

    @Column(name = "visited_count", nullable = false)
    val visitedCount: Int = 0,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)