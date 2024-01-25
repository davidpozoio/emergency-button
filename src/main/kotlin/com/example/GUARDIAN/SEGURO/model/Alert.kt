package com.example.GUARDIAN.SEGURO.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date
import kotlin.jvm.Transient

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

    @Transient
    var userId: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
}