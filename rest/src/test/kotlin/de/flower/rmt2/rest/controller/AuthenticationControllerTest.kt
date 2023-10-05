package de.flower.rmt2.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootTest(properties = ["secrets-test.properties"])
@AutoConfigureMockMvc
class AuthenticationControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

    @Value("\${test.basic.auth.password}")
    lateinit var password: String

    @Test
    fun login_success_returns_jwt() {
        // Zugriff auf geschützte Resource ohne JWT -> 401
        mockMvc.perform(
            get("/invitations")
        )
            .andDo(print())
            .andExpect(status().isUnauthorized)

        // Nun Login und Generierung des Remember-Me Cookies.
        val mvcResult = mockMvc.perform(
            post("/login")
                .content("{ \"username\": \"oliver.blume@yahoo.de\", \"password\": \"$password\" }")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
        val jwtResponse = objectMapper.readValue(mvcResult.response.contentAsString, JwtResponse::class.java)
        assertThat(jwtResponse.username).isEqualTo("oliver.blume@yahoo.de")
        assertThat(jwtResponse.roles).containsExactly("ROLE_PLAYER")

        // Zugriff auf geschützte Resource mit Cookie wiederholen -> 200
        mockMvc.perform(
            get("/invitations")
                .header("Authorization", "Bearer ${jwtResponse.accessToken}")
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

}