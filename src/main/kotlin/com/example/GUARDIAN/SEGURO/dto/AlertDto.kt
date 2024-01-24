package com.example.GUARDIAN.SEGURO.dto

import java.util.Date

class AlertDto{
    var id: Long? = null

    var type: String? = null

    var details: String? = null

    var latitude: Double? = null

    var longitude: Double? = null

    var createdAt: Date = Date(System.currentTimeMillis())

    var name: String? = null
}