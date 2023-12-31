package de.flower.rmt2.db.entity

import de.flower.rmt2.db.entity.event.Event
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "invitation")
class Invitation : AbstractBaseEntity() {

    @Enumerated(EnumType.STRING)
    var status: RSVPStatus? = null

    /**
     * Date of response. Might be updated when user changes his status.
     */
    var date: LocalDateTime? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var event: Event? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null

    var guestName: String? = null

    @OneToMany(mappedBy = "invitation", fetch = FetchType.LAZY)
    @OrderBy("createDate")
    val comments: MutableList<Comment> = ArrayList<Comment>()

}