package de.flower.rmt2.rest.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * @author ChatGPT
 */
@ExtendWith(MockitoExtension::class)
class JwtAuthenticationFilterTest {

    @Mock
    private lateinit var jwtService: JwtService

    @Mock
    private lateinit var userDetailsService: UserDetailsService

    @Mock
    private lateinit var request: HttpServletRequest

    @Mock
    private lateinit var response: HttpServletResponse

    @Mock
    private lateinit var filterChain: FilterChain

    @InjectMocks // Automatically inject mock fields
    private lateinit var testFilter: JwtAuthenticationFilter

    @Test
    fun `doFilterInternal catches UsernameNotFoundException when username is not found`() {
        // Prepare the conditions for the test
        val jwt = "valid_jwt_token" // A stub JWT value; could be any non-null string
        val nonExistentUsername = "nonexistent"

        // Mocking the necessary methods
        `when`(request.getHeader("Authorization")).thenReturn("Bearer $jwt")
        `when`(jwtService.validateJwtToken(jwt)).thenReturn(true)
        `when`(jwtService.getUserNameFromJwtToken(jwt)).thenReturn(nonExistentUsername)
        `when`(userDetailsService.loadUserByUsername(nonExistentUsername)).thenThrow(UsernameNotFoundException(""))

        // Perform the operation to be tested
        assertDoesNotThrow {
            testFilter.doFilter(request, response, filterChain)
        }

        // Verification if the filterChain continued
        verify(filterChain).doFilter(request, response)

        // Additionally, ensure that no authentication was set in the context
        // (You'd need appropriate context handling in your original method to test this)
        // assertNull(SecurityContextHolder.getContext().authentication)
    }
}
