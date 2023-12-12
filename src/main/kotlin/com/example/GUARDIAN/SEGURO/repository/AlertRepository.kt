package com.example.GUARDIAN.SEGURO.repository

import com.example.GUARDIAN.SEGURO.model.Alert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlertRepository: JpaRepository<Alert, Long> {
    fun findById(id: Long?): Alert?
}