package de.flower.rmt2.db.entity

import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass


@MappedSuperclass
abstract class AbstractClubRelatedEntity : AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    var club: Club? = null

    protected constructor()

    protected constructor(club: Club) {
        this.club = club
    }


}
