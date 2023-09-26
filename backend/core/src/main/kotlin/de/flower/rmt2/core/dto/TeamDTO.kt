package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.Team

data class TeamDTO(
    val id: Long,
    val name: String,
    val url: String?
)

fun fromEntity(team: Team): TeamDTO {
    return TeamDTO(
        id = team.id!!,
        name = team.name!!,
        url = team.url
    )
}
