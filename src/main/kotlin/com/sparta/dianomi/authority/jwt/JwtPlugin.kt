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
        // JWT서명에 사용되는 비밀 키
        const val SECRET = "PO4c8z41Hia5gJG3oeuFJMRYBB4Ws4aZ"
        //토큰 발급자
        const val ISSUER = "team.sparta.com"
        //토큰 만료 기간 240시간
        const val ACCESS_TOKEN_EXPIRATION_HOUR: Long = 240
    }


    //JWT 유효성 검사 후에 , 클레임 반환
    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    //subject와 memberName , role을 이용해서 엑세스 토큰 생성
    fun generateAccessToken(subject: String, memberName: String, role: String): String {
        // subject, 만료기간과 role을 설정
        return generateToken(subject, memberName, role, Duration.ofHours(ACCESS_TOKEN_EXPIRATION_HOUR))
    }

    // 클레임,발급자,발급시간,만료시간,서명 키를 사용하여 토큰 생성
   private fun generateToken(subject: String, memberName: String, role: String,expirationPeriod : Duration): String {
       //커스텀 클레임 설정
       val claims: Claims = Jwts.claims()
            .add(mapOf("role" to role, "memberName" to memberName))
            .build()

        //서명 키 , 현재 시간을 설정
        val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()


        // JWT를 생성후 반환
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