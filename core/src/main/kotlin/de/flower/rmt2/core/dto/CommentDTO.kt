package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.Comment

data class CommentDTO(
    val text: String,
    val author: UserDTO
)

fun fromEntity(comment: Comment): CommentDTO {
    return CommentDTO(
        text = comment.text!!,
        author = fromEntity(comment.author!!)
    )
}
