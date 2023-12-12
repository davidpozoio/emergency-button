package com.example.GUARDIAN.SEGURO.controller

import com.example.GUARDIAN.SEGURO.model.User
import com.example.GUARDIAN.SEGURO.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun getAll(): List<User>{
        return userService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long?) = userService.findById(id)

    @PostMapping
    fun save(@RequestBody user: User) = userService.save(user)


    @PatchMapping
    fun patch(@RequestBody user: User) = userService.patch(user)
}