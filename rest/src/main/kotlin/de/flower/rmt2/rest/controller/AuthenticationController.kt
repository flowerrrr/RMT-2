package de.flower.rmt2.rest.controller

import de.flower.rmt2.rest.jwt.JwtService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
class AuthenticationController(
    val authenticationManager: AuthenticationManager,
    val jwtService: JwtService
) {

    @PostMapping(path = ["/login"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody authenticationRequest: AuthenticationRequest, request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password))
        SecurityContextHolder.getContext().authentication = authentication

        val jwt: String = jwtService.generateJwtToken(authentication)

        val userDetails: UserDetails = authentication.principal as UserDetails
        val roles: List<String> = userDetails.getAuthorities().stream()
            .map { item -> item.getAuthority() }
            .collect(Collectors.toList())

        return ResponseEntity.ok<JwtResponse>(
            JwtResponse(jwt, userDetails.username, roles)
        )
    }

    data class AuthenticationRequest(
        val username: String,
        val password: String
    ) {

    }
}