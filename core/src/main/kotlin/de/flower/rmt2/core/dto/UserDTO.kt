package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.User

data class UserDTO(
    val fullname: String?
)

fun fromEntity(user: User): UserDTO {
    return UserDTO(
        fullname = user.fullname
    )
}
