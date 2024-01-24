package com.sparta.dianomi.authority.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin() {

    companion object {
        const val SECRET = "PO4c8z41Hia5gJG3oeuFJMRYBB4Ws4aZ"
        const val ISSUER = "team.sparta.com"
        const val ACCESS_TOKEN_EXPIRATION_HOUR: Long = 240
    }


    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject: String, nickName: String, role: String): String {
        return generateToken(subject, nickName, role, Duration.ofHours(ACCESS_TOKEN_EXPIRATION_HOUR))
    }

    private fun generateToken(subject: String, nickName: String, role: String,expirationPeriod : Duration): String {
        val claims: Claims = Jwts.claims()
            .add(mapOf("role" to role, "nickName" to nickName))
            .build()

        val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(ISSUER)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }
}