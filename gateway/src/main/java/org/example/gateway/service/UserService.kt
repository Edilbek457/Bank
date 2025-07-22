package org.example.gateway.service

import org.example.gateway.model.User
import org.example.gateway.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService (
    private val userRepository: UserRepository
) {
    fun getUserByEmail(email: String): User? {
        return userRepository.getByEmail(email)
    }

    fun getUserById(id: Int): User? {
        return userRepository.getByUserId(id)
    }
}


