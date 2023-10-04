package de.flower.rmt2.core.service

import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtils {
}

fun getUsername(): String {
    val context = SecurityContextHolder.getContext()
    val authentication = context.authentication
    val username = authentication.name
    return username
}