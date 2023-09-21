package de.flower.rmt2.db.entity

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
    private var user: User? = null

}

