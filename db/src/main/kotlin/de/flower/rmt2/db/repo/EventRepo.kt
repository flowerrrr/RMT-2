package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.event.Event
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface EventRepo : JpaRepository<Event, Long> {

    @Deprecated("unused, might be removed")
    fun findByDateTimeAfter(cutoffDate: LocalDateTime): List<Event>

    @Deprecated("unused, might be removed")
    fun findByInvitationsUserEmailAndDateTimeAfter(email: String, dateTimeAfter: LocalDateTime): List<Event>
}