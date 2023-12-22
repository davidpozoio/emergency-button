package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.utils.HttpExceptionUnauthorized
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(HttpExceptionUnauthorized::class)
    fun handleException(ex: HttpExceptionUnauthorized): ResponseEntity<Map<String, String>>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            mapOf("message" to "an error occurred, try later")
        )
    }
}