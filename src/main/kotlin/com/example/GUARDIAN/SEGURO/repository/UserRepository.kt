package com.example.GUARDIAN.SEGURO.repository

import com.example.GUARDIAN.SEGURO.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findById(id: Long?): User?
}