package de.flower.rmt2.core.service

import de.flower.rmt2.db.entity.Comment
import de.flower.rmt2.db.entity.Invitation
import de.flower.rmt2.db.entity.User
import de.flower.rmt2.db.repo.CommentRepo
import de.flower.rmt2.db.repo.InvitationRepo
import io.micrometer.common.util.StringUtils
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CommentService(
    val commentRepo: CommentRepo,
    val invitationRepo: InvitationRepo
) {

    public fun createOrUpdateComment(invitation: Invitation, commentText: String?, user: User) {
        if (commentText == null) {
            // null value means no change
            return
        }
        var comment = commentRepo.findByInvitationAndAuthorOrderByCreateDate(invitation, user).firstOrNull()
        if (comment == null && StringUtils.isNotBlank(commentText)) {
            comment = Comment()
            comment.text = commentText
            comment.author = user
            comment.invitation = invitation
            commentRepo.save(comment)
            invitation.comments.add(comment)
        } else if (comment != null) {
            if (StringUtils.isNotBlank(commentText)) {
                comment.text = commentText
                comment.updateDate = LocalDateTime.now()
            } else {
                // remove comment if text is blank
                commentRepo.delete(comment)
                 invitation.comments.removeIf { it.id == comment.id }
            }
        }
    }

}