package org.example.gateway.security.service

import org.example.gateway.exception.user.UserNotFoundException
import org.example.gateway.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UserNotFoundException.ByEmail(email)

        val password = userRepository

        return User (
            user.getEmail(),
        )
    }
}