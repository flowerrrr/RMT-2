package de.flower.rmt2.rest.controller

import de.flower.rmt2.core.dto.InvitationDTO
import de.flower.rmt2.core.dto.UpdateInvitationDTO
import de.flower.rmt2.core.service.InvitationService
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InvitationsController(val invitationService: InvitationService) {

    @GetMapping(path = ["/invitations"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun invitationsByCurrentUser(): List<InvitationDTO> {
        return invitationService.invitationsByCurrentUser()
    }

    @PostMapping(path = ["/invitations"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateInvitation(@Valid @RequestBody invitation: UpdateInvitationDTO): InvitationDTO {
        return invitationService.updateInvitation(invitation)
    }

    @GetMapping(path = ["/events/{id}/invitations"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun invitationsByEvent(@PathVariable id: Long): List<InvitationDTO> {
        return invitationService.invitationsByEvent(id)
    }

    /**
     * Returns the invitation of the current user for the given event.
     */
    @GetMapping(path = ["/events/{id}/invitation"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun invitationByEvent(@PathVariable id: Long): InvitationDTO {
        return invitationService.invitationByEvent(id)
    }
}