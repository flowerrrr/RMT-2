package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.Invitation
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface InvitationRepo : JpaRepository<Invitation, Long> {

    @EntityGraph(attributePaths = ["event"], type = EntityGraph.EntityGraphType.LOAD) // eager load the event
    fun findTop20ByUserEmailOrderByEventDateTimeDesc(email: String): List<Invitation>

    fun findByEventIdAndUserEmail(eventId: Long, username: String): Invitation?

    @EntityGraph(attributePaths = ["event", "user"], type = EntityGraph.EntityGraphType.LOAD) // eager load the event and user
    fun findByEventIdOrderByStatus(eventId: Long): List<Invitation>
}