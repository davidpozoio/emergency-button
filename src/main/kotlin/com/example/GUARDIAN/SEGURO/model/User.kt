package com.example.GUARDIAN.SEGURO.model

import com.example.GUARDIAN.SEGURO.global.Roles
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
    var email: String = ""
    var password: String = ""
        get() = field
        set(value){
            val encodedPassword = BCryptPasswordEncoder().encode(value)
            field = encodedPassword
        }
    var latitude: Double = (0).toDouble()
    var longitude: Double = (0).toDouble()
    var identification: String = ""
    var role: String = Roles.USER

    fun comparePassword(password: String): Boolean{
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}