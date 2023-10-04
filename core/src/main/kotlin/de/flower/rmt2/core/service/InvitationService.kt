package de.flower.rmt2.core.service

import de.flower.rmt2.core.dto.InvitationDTO
import de.flower.rmt2.core.dto.UpdateInvitationDTO
import de.flower.rmt2.core.dto.fromEntity
import de.flower.rmt2.db.repo.InvitationRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

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
        val email = getUsername()
        val invitation = invitationRepo.findByIdOrNull(id)
        if (invitation == null) {
            throw ResourceNotFoundException("Invitation with id ${id} not found.")
        } else if (invitation.user!!.email != email) {
            throw ResourceNotFoundException("User has no permission to update this invitation.")
        } else {
            invitation.status = updateInvitationDTO.status
            invitationRepo.save(invitation)
            return fromEntity(invitation)
        }
    }

}