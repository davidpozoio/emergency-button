package com.example.GUARDIAN.SEGURO.repository

import com.example.GUARDIAN.SEGURO.model.Alert
import com.example.GUARDIAN.SEGURO.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository: JpaRepository<User, Long> {
    fun findById(id: Long?): User?

}