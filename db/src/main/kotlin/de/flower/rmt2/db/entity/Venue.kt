package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "venue")
class Venue : AbstractClubRelatedEntity() {

    @Column
    var name: String? = null

    @Column
    var address: String? = null

    @Column
    private var lat: Double? = null

    @Column
    private var lng: Double? = null

}
