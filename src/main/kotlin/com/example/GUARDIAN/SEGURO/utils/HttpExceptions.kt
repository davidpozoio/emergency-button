package com.example.GUARDIAN.SEGURO.utils

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class HttpExceptionUnauthorized(message: String): RuntimeException(message)
@ResponseStatus(HttpStatus.NOT_FOUND)
class HttpExceptionNotFound(message: String): RuntimeException(message)

