package de.flower.rmt2.rest.controller

import de.flower.rmt2.core.dto.EventDTO
import de.flower.rmt2.core.service.EventService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Deprecated("unused, might be removed")
@RestController
class EventsController(val eventService: EventService) {

    @GetMapping(path = ["/events"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun eventsByCurrentUser(): List<EventDTO> {
        return eventService.eventsByCurrentUser()
    }
}