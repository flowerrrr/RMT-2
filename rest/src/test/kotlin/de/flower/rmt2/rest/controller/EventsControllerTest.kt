package de.flower.rmt2.rest.controller

import de.flower.rmt2.core.dto.EventDTO
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class EventsControllerTest : AbstractEventControllerTest() {

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun eventsByCurrentUser() {
        val mvcResult = mockMvc.perform(get("/events"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        val type = objectMapper.typeFactory.constructCollectionType(List::class.java, EventDTO::class.java)
        val eventDTOs: List<EventDTO> = objectMapper.readValue(mvcResult.response.contentAsString, type)
        Assertions.assertThat(eventDTOs).hasSize(1)

    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun event() {
        val mvcResult = mockMvc.perform(get("/events/4"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        val eventDTO = objectMapper.readValue(mvcResult.response.contentAsString, EventDTO::class.java)
        Assertions.assertThat(eventDTO).isNotNull()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun event_exists_but_no_invitation() {
        val mvcResult = mockMvc.perform(get("/events/25"))
            .andDo(print())
            .andExpect(status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("Event with event id 25 not found for user oliver.blume@yahoo.de."))
            .andReturn()
    }
}