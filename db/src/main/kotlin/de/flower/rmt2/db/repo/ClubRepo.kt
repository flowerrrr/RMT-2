package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.Club
import org.springframework.data.jpa.repository.JpaRepository

interface ClubRepo : JpaRepository<Club, Long> {
}