package de.flower.rmt2.db.entity.event

import de.flower.rmt2.db.entity.AbstractClubRelatedEntity
import de.flower.rmt2.db.entity.Invitation
import de.flower.rmt2.db.entity.Team
import de.flower.rmt2.db.entity.Venue
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eventType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Event")
class Event : AbstractClubRelatedEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team? = null

    /**
     * Can be null. Sometimes events are created before it is clear where they are held.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    var venue: Venue? = null

    /**
     * Derived field. Mostly used when searching for event by date and ordering by date.
     * Field is updated whenever #setDate() or #setTime() is called.
     */
    @Column
    var dateTime: LocalDateTime? = null

    /**
     * Optional field. Needed for iCalender objects
     */
    @Column
    var dateTimeEnd: LocalDateTime? = null

    @Column
    var summary: String? = null

    @Column
    var comment: String? = null

    @Column
    var invitationSent: Boolean? = null

    @Column
    var canceled = false

//    @ManyToOne
//    var createdBy: User? = null

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    val invitations: List<Invitation> = ArrayList<Invitation>()

    fun getEventType(): EventType {
        return EventType.from(this)
    }

    override fun toString(): String {
        return "Event(id=$id, dateTime=$dateTime, summary=$summary)"
    }
}