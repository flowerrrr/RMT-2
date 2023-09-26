package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.Invitation
import de.flower.rmt2.db.entity.RSVPStatus

data class InvitationDTO(
    val id: Long?,
    val status: RSVPStatus?,
    val event: EventDTO? = null
)

fun fromEntity(invitation: Invitation): InvitationDTO {
    return InvitationDTO(
        id = invitation.id!!,
        status = invitation.status!!,
        event = invitation.event?.let { fromEntity(it) }
    )
}
