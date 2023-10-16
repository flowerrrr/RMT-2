package de.flower.rmt2.rest.controller

import de.flower.rmt2.core.dto.EventDTO
import de.flower.rmt2.core.service.EventService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class EventsController(val eventService: EventService) {

    @GetMapping(path = ["/events"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun eventsByCurrentUser(): List<EventDTO> {
        return eventService.eventsByCurrentUser()
    }

    @GetMapping(path = ["/events/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun eventsByCurrentUser(@PathVariable id: Long): EventDTO {
        return eventService.event(id)
    }
}