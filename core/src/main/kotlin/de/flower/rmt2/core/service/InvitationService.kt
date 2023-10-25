package de.flower.rmt2.core.service

import de.flower.rmt2.core.dto.InvitationDTO
import de.flower.rmt2.core.dto.UpdateInvitationDTO
import de.flower.rmt2.core.dto.fromEntity
import de.flower.rmt2.db.entity.event.Event
import de.flower.rmt2.db.repo.InvitationRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InvitationService(
    val invitationRepo: InvitationRepo
) {

    fun invitationsByCurrentUser(): List<InvitationDTO> {
        val email = getUsername()
        return invitationRepo.findTop20ByUserEmailOrderByEventDateTimeDesc(email)
            .map { fromEntity(it) }
    }

    fun updateInvitation(updateInvitationDTO: UpdateInvitationDTO): InvitationDTO {
        val id = updateInvitationDTO.id
        val username = getUsername()
        val invitation = invitationRepo.findByIdOrNull(id)
        if (invitation == null) {
            throw ResourceNotFoundException("Invitation with id ${id} not found.")
        } else if (invitation.user!!.email != username) {
            // TODO eigene Exception für fehlende Berechtigung verwenden
            throw ResourceNotFoundException("User has no permission to update this invitation.")
        } else if (isEventClosed(invitation.event!!)) {
            // TODO eigene Exception für Validierungsfehler verwenden
            throw ResourceNotFoundException("Event is closed, updating invitation is not allowed.")
        } else {
            invitation.status = updateInvitationDTO.status
            invitationRepo.save(invitation)
            return fromEntity(invitation)
        }
    }

    private fun isEventClosed(event: Event) = event.dateTime!!.isBefore(LocalDateTime.now())

    fun invitationsByEvent(eventId: Long): List<InvitationDTO> {
        // check if user has permission to see invitations for this event
        // wenn keine Einladung zu einem Event vorhanden, darf der User den Event nicht sehen
        val username = getUsername()
        val invitation = invitationRepo.findByEventIdAndUserEmail(eventId, username)
        if (invitation == null) {
            throw ResourceNotFoundException("Invitation with event id $eventId not found for user $username.")
        } else {
            return invitationRepo.findByEventIdOrderByStatusAscDateAsc(eventId)
                .map { fromEntity(it) }
        }

    }

    fun invitationByEvent(eventId: Long): InvitationDTO {
        val username = getUsername()
        val invitation = invitationRepo.findByEventIdAndUserEmail(eventId, username)
        if (invitation == null) {
            throw ResourceNotFoundException("Invitation for event $eventId not found for user $username.")
        } else {
            return fromEntity(invitation)
        }
    }

}