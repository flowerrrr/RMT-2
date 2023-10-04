package de.flower.rmt2.db.entity.event

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalTime

@MappedSuperclass
abstract class AbstractSoccerEvent : Event() {

    @Column
    var kickoff: LocalTime? = null
}