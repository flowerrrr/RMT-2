package de.flower.rmt2.rest.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.RememberMeServices
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    val authenticationManager: AuthenticationManager,
    val rememberMeServices: RememberMeServices
) {

    /**
     * Wenn mit ?remember-me=true aufgerufen, wird ein Remember-Me Cookie gesetzt.
     */
    @PostMapping(path = ["/login"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody authenticationRequest: AuthenticationRequest, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password))
        SecurityContextHolder.getContext().authentication = authentication

        // Manually trigger Remember-Me:
        rememberMeServices.loginSuccess(request, response, authentication)

        return ResponseEntity.ok().body("Logged in successfully!")
    }

    data class AuthenticationRequest(
        val username: String,
        val password: String
    ) {

    }
}