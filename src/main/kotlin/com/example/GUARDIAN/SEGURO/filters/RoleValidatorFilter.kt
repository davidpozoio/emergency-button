package com.example.GUARDIAN.SEGURO.filters

import com.example.GUARDIAN.SEGURO.global.Roles
import com.example.GUARDIAN.SEGURO.service.TokenService
import com.example.GUARDIAN.SEGURO.service.UserService
import com.example.GUARDIAN.SEGURO.utils.HttpExceptionUnauthorized
import com.example.GUARDIAN.SEGURO.utils.getJwtCookie
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpMethod
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
            throw HttpExceptionUnauthorized("the user doesn't have access")
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
//        var allowedMethods = listOf(HttpMethod.POST.toString())
//        if(request.pathInfo == "/alerts"){
//            return allowedMethods.contains(request.method.toString())
//        }
//        return false
       return true
  }
}