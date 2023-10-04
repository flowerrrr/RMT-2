package de.flower.rmt2.core.dto

import de.flower.rmt2.db.entity.event.Event
import de.flower.rmt2.db.entity.event.EventType
import java.time.LocalDateTime

data class EventDTO(
    val id: Long,
    val eventType: EventType,
    val team: TeamDTO,
//        val venue: VenueDTO?,
    val dateTime: LocalDateTime,
    val dateTimeEnd: LocalDateTime,
    val summary: String,
//        val comment: String?,
//        val invitationSent: Boolean?,
    val canceled: Boolean,
    // val createdBy: UserDTO?
    // val opponent: OpponentDTO?
)

fun fromEntity(event: Event): EventDTO {
    return EventDTO(
        id = event.id!!,
        eventType = event.getEventType(),
        team = fromEntity(event.team!!),
        dateTime = event.dateTime!!,
        dateTimeEnd = event.dateTimeEnd!!,
        summary = event.summary!!,
        canceled = event.canceled,
    )
}
