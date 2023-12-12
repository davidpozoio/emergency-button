package com.example.GUARDIAN.SEGURO.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "alert")
class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var type: String? = null
    var details: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    @Column(name="created_at")
    var createdAt: Date = Date(System.currentTimeMillis())
    @Column(name="user_id")
    var userId: Long? = null
}