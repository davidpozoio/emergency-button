package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.dto.LoginDto
import com.example.GUARDIAN.SEGURO.dto.RegisterDto
import com.example.GUARDIAN.SEGURO.dto.UserDto
import com.example.GUARDIAN.SEGURO.model.User
import com.example.GUARDIAN.SEGURO.service.AuthService
import com.example.GUARDIAN.SEGURO.service.TokenService
import com.example.GUARDIAN.SEGURO.utils.createJwtCookie
import com.example.GUARDIAN.SEGURO.utils.getJwtCookie
import com.example.GUARDIAN.SEGURO.utils.parseRegisterDtoToUser
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val tokenService: TokenService
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody user: RegisterDto, bindingResult: BindingResult, response: HttpServletResponse): Any{
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return a ResponseEntity with the error messages
            val errorMessages = bindingResult.allErrors.map { it.defaultMessage }.toList()
            return ResponseEntity.badRequest().body(mapOf("errors" to errorMessages))
        }
        val createdUser = authService.signUp(parseRegisterDtoToUser(user))
        val token = tokenService.create(createdUser.id.toString())
        val cookieJwt = createJwtCookie(token)
        response.addCookie(cookieJwt)

        return ResponseEntity.ok().body(createdUser.apply { password="" })
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody user: LoginDto, bindingResult: BindingResult, response: HttpServletResponse): ResponseEntity<*>{
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return a ResponseEntity with the error messages
            val errorMessages = bindingResult.allErrors.map { it.defaultMessage }.toList()
            return ResponseEntity.badRequest().body(mapOf("errors" to errorMessages))
        }

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

    @GetMapping("/logout")
    fun logout(response: HttpServletResponse){
        val deletedCookie = Cookie("jwt", null)

        // Set the maxAge to 0 to instruct the browser to remove the cookie
        deletedCookie.maxAge = 0

        // Set the path attribute to match the path of the original cookie
        deletedCookie.path = "/"

        // Add the new cookie to the response
        response.addCookie(deletedCookie)
    }
}