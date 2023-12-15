package com.example.GUARDIAN.SEGURO.utils

import com.example.GUARDIAN.SEGURO.dto.LoginDto
import com.example.GUARDIAN.SEGURO.dto.RegisterDto
import com.example.GUARDIAN.SEGURO.model.User

fun parseRegisterDtoToUser(registerDto: RegisterDto): User{
    return User().apply {
        fullName=registerDto.fullName
        gender=registerDto.gender
        identification=registerDto.identification
        latitude=registerDto.latitude
        longitude=registerDto.longitude
        email=registerDto.email
        password = registerDto.password
        role=registerDto.role
    }
}