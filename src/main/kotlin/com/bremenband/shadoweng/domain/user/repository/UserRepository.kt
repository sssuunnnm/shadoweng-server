package com.bremenband.shadoweng.domain.user.repository

import com.bremenband.shadoweng.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByProviderAndProviderId(provider: String, providerId: String): Optional<User>
}