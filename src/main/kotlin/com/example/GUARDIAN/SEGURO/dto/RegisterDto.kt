package com.example.GUARDIAN.SEGURO.dto

import com.example.GUARDIAN.SEGURO.global.Roles

class RegisterDto {
    var fullName: String = ""
    var gender: String = ""
    var email: String = ""
    var password: String = ""

    var latitude: Double = (0).toDouble()
    var longitude: Double = (0).toDouble()
    var identification: String = ""
    var role: String = Roles.USER
}