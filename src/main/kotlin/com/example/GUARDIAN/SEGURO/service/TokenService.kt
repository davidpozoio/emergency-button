package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.global.SecretModule
import com.example.GUARDIAN.SEGURO.utils.HttpExceptionUnauthorized
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

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
            throw HttpExceptionUnauthorized("invalid token")
        }
    }
}