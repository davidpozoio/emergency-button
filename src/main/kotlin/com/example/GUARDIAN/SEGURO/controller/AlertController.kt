package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.model.Alert
import com.example.GUARDIAN.SEGURO.service.AlertService
import com.example.GUARDIAN.SEGURO.service.TokenService
import com.example.GUARDIAN.SEGURO.utils.getJwtCookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:8000", "http://localhost:5173"],
    methods = [RequestMethod.POST, RequestMethod.GET],
    allowCredentials = "true")
@RestController
@RequestMapping("/alerts")
class AlertController {
    @Autowired
    lateinit var alertService: AlertService
    @Autowired
    lateinit var tokenService: TokenService

    @GetMapping
    fun findAll(@RequestParam userId: Long?): ResponseEntity<List<Alert>> {
        userId ?: return ResponseEntity.ok(alertService.findAll())

        return ResponseEntity.ok(alertService.findAllByUserId(userId))
    }

    @PostMapping
    fun save(@RequestBody alert: Alert, request: HttpServletRequest): Alert{
        val cookieJwt = getJwtCookie(request)
        val decodedToken = tokenService.verify(cookieJwt.value)
        alert.apply { userId = decodedToken.subject.toLong() }
        return alertService.save(alert)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = alertService.delete(id)
}