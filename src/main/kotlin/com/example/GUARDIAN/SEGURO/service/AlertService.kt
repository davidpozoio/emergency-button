package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.model.Alert
import com.example.GUARDIAN.SEGURO.repository.AlertRepository
import com.example.GUARDIAN.SEGURO.repository.UserRepository
import com.example.GUARDIAN.SEGURO.utils.HttpExceptionNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class AlertService {
    @Autowired
    lateinit var alertRepository: AlertRepository
    @Autowired
    lateinit var userRepository: UserRepository

    fun findAll(): List<Any> {
        val recentAlerts = alertRepository.findAllWithUserName()

        return recentAlerts.sortedByDescending { it.createdAt }.take(3)
    }
    fun save(alert: Alert): Alert{
        val user = userRepository.findById(alert.userId)?:
        throw HttpExceptionNotFound("user not found")
        return alertRepository.save(alert)
    }

    fun findAllByUserId(id: Long) = alertRepository.findAlertsByUserId(id)

    fun delete(id: Long) = alertRepository.deleteById(id)
}