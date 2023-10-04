package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepo : JpaRepository<Team, Long> {
}