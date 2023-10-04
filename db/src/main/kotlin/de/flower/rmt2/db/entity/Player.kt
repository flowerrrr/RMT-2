package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "player")
class Player : AbstractBaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User? = null

    /**
     * If true, player is not required to respond to invitations.
     */
    @Column
    var optional: Boolean? = null

    /**
     * If false, the player does not receive email-invitations (but can still respond to an event).
     */
    @Column
    var notification: Boolean? = null

    /**
     * If true, the player will not be invited to any future events.
     */
    @Column
    var retired: Boolean? = null

    override fun toString(): String {
        return "Player(id=$id, optional=$optional, notification=$notification, retired=$retired)"
    }

}

