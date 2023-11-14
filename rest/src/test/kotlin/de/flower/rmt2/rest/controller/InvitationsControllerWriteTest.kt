package de.flower.rmt2.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.flower.rmt2.core.dto.InvitationDTO
import de.flower.rmt2.core.dto.UpdateInvitationDTO
import de.flower.rmt2.db.entity.RSVPStatus
import de.flower.rmt2.db.repo.InvitationRepo
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.support.TransactionTemplate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class InvitationsControllerWriteTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
    @Autowired val invitationRepo: InvitationRepo,
    @Autowired val transactionTemplate: TransactionTemplate
) {

    @Test
    @WithMockUser(username = "testuser@mailinator.com")
    fun updateInvitation() {
        // Given
        val invitationId = 4L
        transactionTemplate.execute<Any> {
            val invitation = invitationRepo.findByIdOrNull(invitationId)!!
            assertThat(invitation).isNotNull()
            assertThat(invitation.status).isEqualTo(RSVPStatus.NORESPONSE)
            assertThat(invitation.date).isBefore(LocalDateTime.now())
            assertThat(invitation.comments).isEmpty()
            null // Return value not used
        }

        // When
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(invitationId, RSVPStatus.DECLINED, "I'm not coming!")))
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        // Then
        val invitationDTO = objectMapper.readValue(mvcResult.response.contentAsString, InvitationDTO::class.java)
        assertThat(invitationDTO).isNotNull()
        transactionTemplate.execute<Any> {
            val invitation = invitationRepo.findByIdOrNull(invitationId)!!
            // check comment and data of response
            assertThat(invitation.status).isEqualTo(RSVPStatus.DECLINED)
            // Date should be set to now
            assertThat(invitation.date).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS))
            assertThat(invitation.comments).hasSize(1)
            val comment = invitation.comments[0]
            assertThat(comment.text).isEqualTo("I'm not coming!")
        }
    }

    @Test
    @WithMockUser(username = "testuser@mailinator.com")
    fun updateInvitationDoesNotUpdateDateIfStatusIsUnchanged() {
        // Given
        val invitationId = 4L
        val invitation = invitationRepo.findByIdOrNull(invitationId)!!
        invitation.status = RSVPStatus.ACCEPTED
        val invitationDate = LocalDateTime.of(2019, 1, 1, 0, 0, 0)
        invitation.date = invitationDate
        invitationRepo.save(invitation)

        // When
        val mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(invitationId, RSVPStatus.ACCEPTED, "I'm coming!")))
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        // Then
        val invitationDTO = objectMapper.readValue(mvcResult.response.contentAsString, InvitationDTO::class.java)
        assertThat(invitationDTO.date).isEqualTo(invitationDate)
    }

    @Test
    @WithMockUser(username = "testuser@mailinator.com")
    fun updateInvitationAndDeleteComment() {
        // Given
        val invitationId = 4L
        transactionTemplate.execute<Any> {
            val invitation = invitationRepo.findByIdOrNull(invitationId)!!
            assertThat(invitation).isNotNull()
            if (invitation.comments.isEmpty()) {
                // erzeuge Kommentar
                updateInvitation()
            }
            null // Return value not used
        }

        // When using null as comment text the comment should not be modified
        var mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(invitationId, RSVPStatus.ACCEPTED, null)))
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        // Then
        var invitationDTO = objectMapper.readValue(mvcResult.response.contentAsString, InvitationDTO::class.java)
        transactionTemplate.execute<Any> {
            val invitation = invitationRepo.findByIdOrNull(invitationId)!!
            assertThat(invitation.comments).isNotEmpty
        }

        // Only when using an empty string the comment should be deleted
        mvcResult = mockMvc.perform(
            post("/invitations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(UpdateInvitationDTO(invitationId, RSVPStatus.ACCEPTED, "")))
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        // Then
        invitationDTO = objectMapper.readValue(mvcResult.response.contentAsString, InvitationDTO::class.java)
        transactionTemplate.execute<Any> {
            val invitation = invitationRepo.findByIdOrNull(invitationId)!!
            assertThat(invitation.comments).isEmpty()
        }
    }
}