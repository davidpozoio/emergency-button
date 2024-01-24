package com.example.GUARDIAN.SEGURO.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date

@Entity
@Table(name = "alert")
class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @field:NotBlank(message = "type is required")
    var type: String? = null

    @field:NotBlank(message = "details is required")
    var details: String? = null

    @field:NotNull(message = "latitude is required")
    var latitude: Double? = null

    @field:NotNull(message = "longitude is required")
    var longitude: Double? = null

    @Column(name="created_at")
    var createdAt: Date = Date(System.currentTimeMillis())

    @Column(name="user_id")
    var userId: Long? = null

}