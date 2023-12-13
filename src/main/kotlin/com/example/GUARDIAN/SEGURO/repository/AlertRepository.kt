package com.example.GUARDIAN.SEGURO.repository

import com.example.GUARDIAN.SEGURO.model.Alert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AlertRepository: JpaRepository<Alert, Long> {
    fun findById(id: Long?): Alert?

    @Query("SELECT alert.* FROM users INNER JOIN alert on users.id = alert.user_id where users.id = :id",
        nativeQuery = true)
    fun findAlertsByUserId(id: Long): List<Alert>
}