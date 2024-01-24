package com.example.GUARDIAN.SEGURO.config

import com.example.GUARDIAN.SEGURO.filters.RoleValidatorFilter
import com.example.GUARDIAN.SEGURO.global.Roles
import com.example.GUARDIAN.SEGURO.service.TokenService
import com.example.GUARDIAN.SEGURO.service.UserService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
class RoleValidatorConfig {
    @Bean
    fun userRoleValidatorFilter(tokenService: TokenService, userService: UserService): FilterRegistrationBean<RoleValidatorFilter>{
        val registrationBean = FilterRegistrationBean<RoleValidatorFilter>()
        registrationBean.filter = RoleValidatorFilter(tokenService,
            userService,
            listOf(Roles.USER, Roles.ADMIN))

        val allowedMethods = listOf(HttpMethod.POST.toString())
        registrationBean.addUrlPatterns("/alerts/*")

        return  registrationBean
    }

    @Bean
    fun adminRoleValidatorFilter(tokenService: TokenService, userService: UserService): FilterRegistrationBean<RoleValidatorFilter>{
        val registrationBean = FilterRegistrationBean<RoleValidatorFilter>()
        registrationBean.filter = RoleValidatorFilter(tokenService,
            userService,
            listOf(Roles.ADMIN))
        registrationBean.addUrlPatterns("/users/*")
        return  registrationBean
    }
}