package com.example.GUARDIAN.SEGURO.repository

import com.example.GUARDIAN.SEGURO.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
   fun findById(id: Long?): User?

   @Query("SELECT * FROM users WHERE email = :email", nativeQuery = true)
   fun findByEmail(@Param("email") email: String): User?

}