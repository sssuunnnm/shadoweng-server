package com.bremenband.shadoweng.domain.video.repository

import com.bremenband.shadoweng.domain.video.entity.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video, String>