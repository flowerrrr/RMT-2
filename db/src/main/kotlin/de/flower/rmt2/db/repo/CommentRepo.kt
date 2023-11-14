package de.flower.rmt2.db.repo

import de.flower.rmt2.db.entity.Comment
import de.flower.rmt2.db.entity.Invitation
import de.flower.rmt2.db.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepo : JpaRepository<Comment, Long> {

    fun findByInvitationAndAuthorOrderByCreateDate(invitation: Invitation, user: User): List<Comment>

}