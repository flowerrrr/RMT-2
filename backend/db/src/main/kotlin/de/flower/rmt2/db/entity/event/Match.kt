package de.flower.rmt2.db.entity.event

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("Match")
class Match : AbstractSoccerEvent() {

    //    /**
//     * Defined here to be able to eager fetch this association with query dsl.
//     * Can be null. Sometimes events are created without knowing who the opponent will be.
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    var opponent: Opponent? = null

}