package com.example.GUARDIAN.SEGURO.config

import com.example.GUARDIAN.SEGURO.filters.RoleValidatorFilter
import com.example.GUARDIAN.SEGURO.global.Roles
import com.example.GUARDIAN.SEGURO.service.TokenService
import com.example.GUARDIAN.SEGURO.service.UserService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RoleValidatorConfig {
    @Bean
    fun userRoleValidatorFiler(tokenService: TokenService, userService: UserService): FilterRegistrationBean<RoleValidatorFilter>{
        val registrationBean = FilterRegistrationBean<RoleValidatorFilter>()
        registrationBean.filter = RoleValidatorFilter(tokenService,
            userService,
            listOf(Roles.USER, Roles.ADMIN))
        registrationBean.addUrlPatterns("/alerts/*")
        registrationBean.order = 1
        return  registrationBean
    }

    @Bean
    fun adminRoleValidatorFiler(tokenService: TokenService, userService: UserService): FilterRegistrationBean<RoleValidatorFilter>{
        val registrationBean = FilterRegistrationBean<RoleValidatorFilter>()
        registrationBean.filter = RoleValidatorFilter(tokenService,
            userService,
            listOf(Roles.ADMIN))
        registrationBean.addUrlPatterns("/users/*")
        registrationBean.order = 2
        return  registrationBean
    }
}