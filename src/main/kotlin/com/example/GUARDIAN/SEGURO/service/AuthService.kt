package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.model.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException

@Service
class AuthService (
    private val userService: UserService,
    private val tokenService: TokenService
){

    fun signUp(user: User): User{
        return userService.save(user)
    }
    fun login(email: String, password: String): String{
        val user = userService.findByEmail(email)
        if(!user.comparePassword(password)){
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "incorrect password")
        }
        return tokenService.create(user.id.toString())
    }
}