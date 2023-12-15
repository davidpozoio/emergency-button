package com.example.GUARDIAN.SEGURO.utils

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.util.WebUtils

fun getJwtCookie(request: HttpServletRequest): Cookie{
    val jwt = WebUtils.getCookie(request, "jwt")
    return jwt?:throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "jwt not provided")
}

fun createJwtCookie(token: String): Cookie{
    val cookie = Cookie("jwt", token)
    cookie.path = "/"
    cookie.isHttpOnly = true

    return cookie
}