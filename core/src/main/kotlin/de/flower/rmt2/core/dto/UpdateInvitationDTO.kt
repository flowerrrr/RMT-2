package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.RSVPStatus
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateInvitationDTO(

    @field:NotNull
    val id: Long?,

    @field:NotNull
    val status: RSVPStatus?,

    @field:Size(max = 255)
    val comment: String?
)