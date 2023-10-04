package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepo : JpaRepository<Player, Long> {
}