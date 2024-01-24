package com.example.GUARDIAN.SEGURO.dto

import com.example.GUARDIAN.SEGURO.global.Roles
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class RegisterDto {
    @field:NotBlank(message = "fullName is required")
    var fullName: String = ""

    @field:NotBlank(message = "gender is required")
    var gender: String = ""

    @field:NotBlank(message = "gender is required")
    @field:Email(message = "invalid email")
    var email: String = ""

    @field:NotBlank(message = "password is required")
    var password: String = ""

    @field:NotNull(message = "latitude is required")
    var latitude: Double = (0).toDouble()

    @field:NotNull(message = "longitude is required")
    var longitude: Double = (0).toDouble()

    @field:NotNull(message = "identification is required")
    var identification: String = ""

    @field:NotBlank(message = "latitude is required")
    var role: String = Roles.USER
}