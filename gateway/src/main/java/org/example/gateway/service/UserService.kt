package org.example.gateway.service

import org.example.gateway.model.User
import org.example.gateway.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService (
    private val userRepository: UserRepository
) {
    fun getUserByEmail(email: String): Mono<User?> {
        return userRepository.getByEmail(email)
    }

    fun getUserById(id: Long): Mono<User?> {
        return userRepository.findById(id)
    }

    fun userExistsByEmail(email: String): Mono<Boolean> {
        return userRepository.getByEmail(email).hasElement()
    }
}


