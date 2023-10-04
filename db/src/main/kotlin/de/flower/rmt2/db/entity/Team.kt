package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "team")
class Team : AbstractClubRelatedEntity() {

    @Column
    var name: String? = null

    @Column
    var url: String? = null

    @OneToMany(mappedBy = "team")
    val players: List<Player> = ArrayList<Player>()

    override fun toString(): String {
        return "Team(id=$id, name=$name)"
    }

}