package com.example.GUARDIAN.SEGURO

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class GuardianSeguroApplication

fun main(args: Array<String>) {
	runApplication<GuardianSeguroApplication>("-jvm-target")
}
