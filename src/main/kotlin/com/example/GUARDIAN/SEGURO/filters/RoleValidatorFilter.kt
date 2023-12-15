package com.example.GUARDIAN.SEGURO.filters

import com.example.GUARDIAN.SEGURO.global.Roles
import com.example.GUARDIAN.SEGURO.service.TokenService
import com.example.GUARDIAN.SEGURO.service.UserService
import com.example.GUARDIAN.SEGURO.utils.getJwtCookie
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.filter.OncePerRequestFilter

class RoleValidatorFilter @Lazy @Autowired constructor(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val roles: List<String> = listOf(Roles.USER)
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtCookie = getJwtCookie(request)
        val decodedToken = tokenService.verify(jwtCookie.value)

        val userId = decodedToken.subject
        val user = userService.findById(userId.toLong())

        val hasUserAccess = roles.contains(user.role)

        if(!hasUserAccess){
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "user doesn't have access")
        }

        filterChain.doFilter(request, response)
    }
}