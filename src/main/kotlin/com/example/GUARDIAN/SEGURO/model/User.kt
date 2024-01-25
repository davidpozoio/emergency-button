package com.example.GUARDIAN.SEGURO.model

import com.example.GUARDIAN.SEGURO.global.Roles
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "full_name")
    var fullName: String = ""
    var gender: String = ""
    @JsonIgnore
    var email: String = ""
    @JsonIgnore
    var password: String = ""
        get() = field
        set(value){
            val encodedPassword = BCryptPasswordEncoder().encode(value)
            field = encodedPassword
        }
    @JsonIgnore
    var latitude: Double = (0).toDouble()
    @JsonIgnore
    var longitude: Double = (0).toDouble()
    @JsonIgnore
    var identification: String = ""
    @JsonIgnore
    var role: String = Roles.USER

    fun comparePassword(password: String): Boolean{
        return BCryptPasswordEncoder().matches(password, this.password)
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var alert: List<Alert>? = null
}