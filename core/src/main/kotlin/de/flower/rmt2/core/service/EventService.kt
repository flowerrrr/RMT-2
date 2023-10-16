package de.flower.rmt2.core.service

import de.flower.rmt2.core.dto.EventDTO
import de.flower.rmt2.core.dto.InvitationDTO
import de.flower.rmt2.core.dto.fromEntity
import de.flower.rmt2.db.repo.EventRepo
import de.flower.rmt2.db.repo.InvitationRepo
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EventService(
    val eventRepo: EventRepo,
    val invitationRepo: InvitationRepo
) {

    fun eventsByCurrentUser(): List<EventDTO> {
        val email = getUsername()
        val dateTimeAfter = LocalDateTime.now().minus(30, java.time.temporal.ChronoUnit.DAYS)
        return eventRepo.findByInvitationsUserEmailAndDateTimeAfter(email, dateTimeAfter)
            .map { fromEntity(it) }
    }

    /**
     * Ein User kann nur Events aufrufen, für die er eingeladen wurde.
     */
    fun event(eventId: Long): EventDTO {
        // check if user has permission to see this event
        val username = getUsername()
        val invitation = invitationRepo.findByEventIdAndUserEmail(eventId, username)
        if (invitation == null) {
            throw ResourceNotFoundException("Event with event id $eventId not found for user $username.")
        } else {
            val event = eventRepo.getReferenceById(eventId)
            return fromEntity(event)
        }
    }

}