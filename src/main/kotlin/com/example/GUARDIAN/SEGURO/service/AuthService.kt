package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.model.User
import com.example.GUARDIAN.SEGURO.utils.HttpExceptionUnauthorized
import org.springframework.stereotype.Service

data class AuthUser(val role: String,
                    val token: String,
                    val fullName: String,
                    val email: String,
                    val latitude: Double,
                    val longitude: Double)

@Service
class AuthService (
    private val userService: UserService,
    private val tokenService: TokenService
){

    fun signUp(user: User): User{
        return userService.save(user)
    }
    fun login(email: String, password: String): AuthUser{
        val user = userService.findByEmail(email)
        if(!user.comparePassword(password)){
            throw HttpExceptionUnauthorized("incorrect password")
        }

        return AuthUser(role = user.role,
            token = tokenService.create(user.id.toString()),
            fullName = user.fullName,
            email = user.email,
            latitude = user.latitude,
            longitude = user.longitude
            )
    }

    fun isAuth(token: String): User{
        val decodedToken = tokenService.verify(token)
        val userId = decodedToken.subject.toLong()
        return userService.findById(userId)
    }
}