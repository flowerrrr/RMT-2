package de.flower.rmt2.rest.controller

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ApiErrorTest : AbstractEventControllerTest() {

    @Test
    fun error_page_is_accessible_for_unauthorized_user() {
        val mvcResult = mockMvc.perform(get("/error"))
            .andDo(print())
            .andExpect(status().isInternalServerError)
            .andExpect(content().string(Matchers.matchesRegex("\\{\"timestamp\":\".*\",\"status\":999,\"error\":\"None\"\\}")))
            .andReturn()

    }


}