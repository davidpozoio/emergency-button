package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.utils.HttpExceptionUnauthorized
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(HttpExceptionUnauthorized::class)
    fun handleException(ex: HttpExceptionUnauthorized): ResponseEntity<Map<String, String>>{
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            mapOf("message" to ex.message!!)
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleInternalServerError(ex: Exception): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            mapOf("message" to "there was an error: ${ex.message}")
        )
    }
}