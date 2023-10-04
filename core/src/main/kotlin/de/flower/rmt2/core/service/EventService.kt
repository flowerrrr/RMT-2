package de.flower.rmt2.core.service

import de.flower.rmt2.core.dto.EventDTO
import de.flower.rmt2.core.dto.fromEntity
import de.flower.rmt2.db.repo.EventRepo
import de.flower.rmt2.db.repo.InvitationRepo
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Deprecated("Use InvitationService instead. This class might be removed.")
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

}