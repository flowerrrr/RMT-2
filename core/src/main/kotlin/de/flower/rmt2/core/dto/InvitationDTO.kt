package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.Invitation
import de.flower.rmt2.db.entity.RSVPStatus
import java.time.LocalDateTime

data class InvitationDTO(
    val id: Long?,
    val status: RSVPStatus?,
    val date: LocalDateTime? = null,
    val event: EventDTO? = null,
    val user: UserDTO? = null,
    val comments: List<CommentDTO> = emptyList()
)

/**
 * This is a workaround for the fact that we don't have a user for guest invitations.
 */
private const val GUEST_USERNAME = "guest@das-tool"

fun fromEntity(invitation: Invitation): InvitationDTO {
    return InvitationDTO(
        id = invitation.id!!,
        status = invitation.status!!,
        date = invitation.date,
        event = invitation.event?.let { fromEntity(it) },
        user = invitation.user?.let { fromEntity(it) } ?: UserDTO(GUEST_USERNAME, invitation.guestName!!),
        comments = invitation.comments.map { fromEntity(it) }
    )
}
