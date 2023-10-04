package de.flower.rmt2.rest.config

import de.flower.rmt2.rest.security.UserDetailServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.RememberMeServices
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

private const val REMEMBER_ME_KEY = "remember-me-key-v-1"

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            cors { }
            csrf { disable() }
            authorizeRequests {
                authorize("/favicon.ico", permitAll)
                authorize("/index.html", permitAll)
                authorize("/login", permitAll)
                authorize(anyRequest, authenticated)
            }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            rememberMe {
                rememberMeServices = rememberMeServices(userDetailsService())
            }
            httpBasic {}
        }
        return http.build();
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    /**
     * https://stackoverflow.com/questions/71281032/spring-security-exposing-authenticationmanager-without-websecurityconfigureradap/72598317#72598317
     */
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.getAuthenticationManager()
    }


    @Bean
    fun rememberMeServices(userDetailsService: UserDetailsService): RememberMeServices {
        val rememberMeServices = TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService)
        rememberMeServices.setParameter("remember-me") // This is the name of the checkbox input in your login form
        rememberMeServices.setTokenValiditySeconds(31536000) // expires after 1 year
        return rememberMeServices
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
