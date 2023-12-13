package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.model.Alert
import com.example.GUARDIAN.SEGURO.service.AlertService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/alerts")
class AlertController {
    @Autowired
    lateinit var alertService: AlertService

    @GetMapping
    fun findAll(@RequestParam userId: Long?): List<Alert>{
        userId?: return alertService.findAll()

        return alertService.findAllByUserId(userId)
    }


    @PostMapping
    fun save(@RequestBody alert: Alert) = alertService.save(alert)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = alertService.delete(id)
}