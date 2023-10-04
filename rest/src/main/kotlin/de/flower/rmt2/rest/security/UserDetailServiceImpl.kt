package de.flower.rmt2.rest.security

import de.flower.rmt2.db.entity.User
import de.flower.rmt2.db.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailServiceImpl : UserDetailsService {

    @Autowired
    lateinit var userRepo: UserRepo

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepo.findByEmail(username)
            ?: throw UsernameNotFoundException("Username {$username} not found")
        return RmtUserDetails(user)
    }
}

