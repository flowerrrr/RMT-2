package de.flower.rmt2.rest.controller

data class JwtResponse(val accessToken: String, val username: String, val roles: List<String>) {
    val tokenType = "Bearer"

}