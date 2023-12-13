package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.model.Alert
import com.example.GUARDIAN.SEGURO.repository.AlertRepository
import com.example.GUARDIAN.SEGURO.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException

@Service
class AlertService {
    @Autowired
    lateinit var alertRepository: AlertRepository
    @Autowired
    lateinit var userRepository: UserRepository

    fun findAll() = alertRepository.findAll()

    fun save(alert: Alert): Alert{
        val user = userRepository.findById(alert.userId)?:
        throw Exception("user not found")
        return alertRepository.save(alert)
    }

    fun findAllByUserId(id: Long) = alertRepository.findAlertsByUserId(id)

}