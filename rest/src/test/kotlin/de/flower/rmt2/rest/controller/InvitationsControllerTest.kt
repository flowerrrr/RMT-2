package de.flower.rmt2.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.flower.rmt2.core.dto.InvitationDTO
import de.flower.rmt2.core.dto.UpdateInvitationDTO
import de.flower.rmt2.db.entity.RSVPStatus
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class InvitationsControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun invitationsByCurrentUser() {
        val mvcResult = mockMvc.perform(get("/invitations"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        val type = objectMapper.typeFactory.constructCollectionType(List::class.java, InvitationDTO::class.java)
        val invitationDTOs: List<InvitationDTO> = objectMapper.readValue(mvcResult.response.contentAsString, type)
        Assertions.assertThat(invitationDTOs).hasSize(1)
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun updateInvitation() {
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(1, RSVPStatus.ACCEPTED)))
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        val invitationDTO = objectMapper.readValue(mvcResult.response.contentAsString, InvitationDTO::class.java)
        Assertions.assertThat(invitationDTO).isNotNull()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun updateInvitationWithInvalidIdReturns404() {
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(666, RSVPStatus.ACCEPTED)))
        )
            .andDo(print())
            .andExpect(status().isNotFound)
            .andExpect(content().string("Invitation with id 666 not found."))
            .andReturn()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun updateInvitationWithInvalidUserReturns404() {
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(2, RSVPStatus.ACCEPTED)))
        )
            .andDo(print())
            .andExpect(status().isNotFound)
            .andExpect(content().string("User has no permission to update this invitation."))
            .andReturn()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun updateInvitationWithEmptyIdReturns400() {
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(null, RSVPStatus.ACCEPTED)))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(content().string("{\"id\":\"must not be null\"}"))
            .andReturn()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun updateInvitationWithEmptyStatusReturns400() {
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(1, null)))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(content().string("{\"status\":\"must not be null\"}"))
            .andReturn()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun invitationsByEvent() {
        val mvcResult = mockMvc.perform(get("/events/4/invitations"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        val type = objectMapper.typeFactory.constructCollectionType(List::class.java, InvitationDTO::class.java)
        val invitationDTOs: List<InvitationDTO> = objectMapper.readValue(mvcResult.response.contentAsString, type)
        Assertions.assertThat(invitationDTOs).hasSize(5)
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun invitationsByEvent_notFound() {
        val mvcResult = mockMvc.perform(get("/events/5/invitations"))
            .andDo(print())
            .andExpect(status().isNotFound)
            .andReturn()
    }
}