package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, Long> {
}