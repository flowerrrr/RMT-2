package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "comment")
class Comment : AbstractBaseEntity() {

    companion object {
        private const val MAXLENGTH = 255
    }

    @Column(length = MAXLENGTH)
    var text: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var invitation: Invitation? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var author: User? = null

}