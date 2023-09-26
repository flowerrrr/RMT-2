package de.flower.rmt2.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.flower.rmt2.core.dto.EventDTO
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class EventsControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

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
}