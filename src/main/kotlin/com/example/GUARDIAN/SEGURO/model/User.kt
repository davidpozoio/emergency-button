package com.example.GUARDIAN.SEGURO.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "full_name")
    var fullName: String? = null
    var gender: String? = null
    var email: String? = null
    var password: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var identification: String? = null
    var role: String? = null
}