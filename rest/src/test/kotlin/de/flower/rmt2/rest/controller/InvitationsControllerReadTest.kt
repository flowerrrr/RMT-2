package de.flower.rmt2.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.flower.rmt2.core.dto.InvitationDTO
import org.assertj.core.api.Assertions.assertThat
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class InvitationsControllerReadTest(
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
        assertThat(invitationDTOs).hasSize(1)
        val invitationDTO = invitationDTOs[0]
        assertThat(invitationDTO.comments).hasSize(2)
        val comment = invitationDTO.comments[0]
        assertThat(comment.author.username).isNotNull()
        assertThat(comment.author.fullname).isNotNull()
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
        assertThat(invitationDTOs).hasSize(5)
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun invitationsByEvent_notFound() {
        val mvcResult = mockMvc.perform(get("/events/5/invitations"))
            .andDo(print())
            .andExpect(status().isNotFound)
            .andReturn()
    }

    @Test
    @WithMockUser(username = "oliver.blume@yahoo.de")
    fun invitationByEvent() {
        val mvcResult = mockMvc.perform(get("/events/4/invitation"))
            .andDo(print())
            .andExpect(status().isOk)
            .andReturn()
        val invitationDTO = objectMapper.readValue(mvcResult.response.contentAsString, InvitationDTO::class.java)
        assertThat(invitationDTO).isNotNull()
    }

    @Test
    @WithMockUser(username = "someone.else@yahoo.de")
    fun `invationByEvent - when no invitation is present for current user 404 is returned`() {
        val mvcResult = mockMvc.perform(get("/events/4/invitation"))
            .andDo(print())
            .andExpect(status().isNotFound)
            .andExpect(content().string("Invitation for event 4 not found for user someone.else@yahoo.de."))
            .andReturn()
    }
}