package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.dto.LoginDto
import com.example.GUARDIAN.SEGURO.dto.RegisterDto
import com.example.GUARDIAN.SEGURO.dto.UserDto
import com.example.GUARDIAN.SEGURO.model.User
import com.example.GUARDIAN.SEGURO.service.AuthService
import com.example.GUARDIAN.SEGURO.utils.createJwtCookie
import com.example.GUARDIAN.SEGURO.utils.getJwtCookie
import com.example.GUARDIAN.SEGURO.utils.parseRegisterDtoToUser
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
    fun login(@RequestBody user: LoginDto, response: HttpServletResponse): ResponseEntity<UserDto>{
        val authUser = authService.login(user.email, user.password)
        val jwtCookie = createJwtCookie(authUser.token)
        response.addCookie(jwtCookie)
        return ResponseEntity.ok(UserDto().apply {
            role = authUser.role
            fullName = authUser.fullName
            email = authUser.email
            latitude = authUser.latitude
            longitude = authUser.longitude
        })
    }

    @GetMapping("/me")
    fun isAuth(response: HttpServletRequest): ResponseEntity<UserDto>{
        val jwtCookie = getJwtCookie(response)
        val user = authService.isAuth(jwtCookie.value)



        return ResponseEntity.ok(UserDto().apply {
            fullName = user.fullName
            email = user.email
            role = user.role
            latitude = user.latitude
            longitude = user.longitude
        })
    }
}