package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "club")
class Club : AbstractBaseEntity() {

    @Column
    var name: String? = null

    override fun toString(): String {
        return "Club(id=$id, name=$name)"
    }

}