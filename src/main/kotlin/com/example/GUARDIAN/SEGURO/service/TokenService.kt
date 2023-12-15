package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.global.SecretModule
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException

@Service
class TokenService {
    fun create(subject: String): String{
        return Jwts.builder()
            .signWith(SecretModule.SECRET)
            .subject(subject)
            .compact()
    }

    fun verify(token: String): Claims{
        try {
            val claims = Jwts.parser().verifyWith(SecretModule.SECRET)
                .build().parseSignedClaims(token)
            return claims.payload
        }catch (err: Exception){
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "invalid token")
        }
    }
}