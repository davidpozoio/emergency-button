package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.dto.LoginDto
import com.example.GUARDIAN.SEGURO.dto.RegisterDto
import com.example.GUARDIAN.SEGURO.model.User
import com.example.GUARDIAN.SEGURO.service.AuthService
import com.example.GUARDIAN.SEGURO.utils.createJwtCookie
import com.example.GUARDIAN.SEGURO.utils.parseRegisterDtoToUser
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody user: RegisterDto): User{
       return authService.signUp(parseRegisterDtoToUser(user))
    }

    @PostMapping("/login")
    fun login(@RequestBody user: LoginDto, response: HttpServletResponse): ResponseEntity<String>{
        val token = authService.login(user.email, user.password)
        val jwtCookie = createJwtCookie(token)
        response.addCookie(jwtCookie)
        return ResponseEntity.ok("success")
    }
}