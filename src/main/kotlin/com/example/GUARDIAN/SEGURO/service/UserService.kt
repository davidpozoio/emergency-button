package com.example.GUARDIAN.SEGURO.service

import com.example.GUARDIAN.SEGURO.model.Alert
import com.example.GUARDIAN.SEGURO.model.User
import com.example.GUARDIAN.SEGURO.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository;

    fun getAll(): List<User>{
        return userRepository.findAll()
    }

    fun findById(id: Long?): User{
        return userRepository.findById(id)?:
        throw HttpClientErrorException(HttpStatus.NOT_FOUND, "user not found")

    }

    fun findByEmail(email: String) = userRepository.findByEmail(email)?:
    throw HttpClientErrorException(HttpStatus.NOT_FOUND, "user not found")
    fun save(user: User): User{
        return userRepository.save(user)
    }

    fun patch(user: User): User{
        val currentUser = userRepository.findById(user.id)?:
        throw HttpClientErrorException(HttpStatus.NOT_FOUND, "user not found")

        val patchedUser = currentUser.apply {
            fullName = user.fullName
            gender = user.gender
        }

        return userRepository.save(patchedUser)
    }

    fun delete(id: Long){
        return userRepository.deleteById(id)
    }
}