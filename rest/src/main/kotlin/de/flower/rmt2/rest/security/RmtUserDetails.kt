package de.flower.rmt2.rest.security

import de.flower.rmt2.db.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

class RmtUserDetails(private val user: User) : org.springframework.security.core.userdetails.User(
    user.username,
    user.encryptedPassword,
    user.isEnabled,
    true,
    true,
    true,
    getGrantedAuthorities(user)
) {

    companion object {

        private fun getGrantedAuthorities(user: User): List<GrantedAuthority> {
            // Uncomment the lines below when you have the roles available in the User object
            // val authorities = user.roles.map { it.authority }

            val authorities = listOf("ROLE_PLAYER") // This is a hardcoded role for now
            return AuthorityUtils.createAuthorityList(*authorities.toTypedArray())
        }
    }
}

