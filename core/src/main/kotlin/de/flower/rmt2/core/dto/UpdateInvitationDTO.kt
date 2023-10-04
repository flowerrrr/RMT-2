package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.RSVPStatus
import jakarta.validation.constraints.NotNull

data class UpdateInvitationDTO(

    @field:NotNull
    val id: Long?,

    @field:NotNull
    val status: RSVPStatus?
)