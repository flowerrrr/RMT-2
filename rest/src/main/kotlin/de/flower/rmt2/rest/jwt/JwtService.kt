package de.flower.rmt2.rest.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import javax.crypto.SecretKey
import java.util.*

@Service
class JwtService {

    @Value("\${app.jwtSecret}")
    lateinit var jwtSecret: String

    private val logger = LoggerFactory.getLogger(javaClass)

    fun generateJwtToken(authentication: Authentication): String {

        val userPrincipal: UserDetails = authentication.getPrincipal() as UserDetails;

        val oneYear = 1000L * 60 * 60 * 24 * 365 // 1 year in milliseconds
        return Jwts.builder()
            .subject(userPrincipal.getUsername())
            .issuedAt(Date())
            .expiration(Date((Date()).getTime() + 5 * oneYear))
            .signWith(key(), Jwts.SIG.HS256)
            .compact();
    }

    fun key(): SecretKey {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parser()
            .verifyWith(key()).build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject()
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key())
                .build()
                .parse(authToken)
            return true
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }
}