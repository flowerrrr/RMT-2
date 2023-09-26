package de.flower.rmt2.rest.config

import de.flower.rmt2.rest.security.UserDetailServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeRequests {
                authorize("/favicon.ico", permitAll)
                authorize("/index.html", permitAll)
                authorize(anyRequest, authenticated)
            }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            httpBasic {}
        }
        return http.build();
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailServiceImpl()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return MessageDigestPasswordEncoder("MD5")
    }

}
