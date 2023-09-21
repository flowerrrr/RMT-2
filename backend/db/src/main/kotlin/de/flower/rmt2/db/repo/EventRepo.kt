package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepo : JpaRepository<Event, Long> {
}