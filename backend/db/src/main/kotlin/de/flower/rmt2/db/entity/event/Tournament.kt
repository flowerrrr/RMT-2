package de.flower.rmt2.db.entity.event

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("Tournament")
class Tournament : AbstractSoccerEvent() {
}