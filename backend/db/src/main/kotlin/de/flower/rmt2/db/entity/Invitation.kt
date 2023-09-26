package de.flower.rmt2.db.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "invitation")
class Invitation : AbstractBaseEntity() {

    @Enumerated(EnumType.STRING)
    var status: RSVPStatus? = null

    /**
     * Date of response. Might be updated when user changes his status.
     */
    var date: Date? = null

//    @OneToMany(mappedBy = "invitation", cascade = CascadeType.REMOVE)
//    @OrderBy("createDate")
//    val comments: List<Comment> = ArrayList<Comment>()

    @ManyToOne(fetch = FetchType.LAZY)
    var event: Event? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null

}