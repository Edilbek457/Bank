package org.example.gateway.security.service

import org.example.gateway.exception.user.UserNotFoundException
import org.example.gateway.repository.PasswordRepository
import org.example.gateway.repository.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordRepository: PasswordRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(email: String): Mono<UserDetails> {
        return userRepository.findByEmail(email)
            .switchIfEmpty(Mono.error(UserNotFoundException.ByEmail(email)))
            .flatMap { user ->
                passwordRepository.findById(user.getId())
                    .map { password ->
                        User.builder()
                            .username(user.getEmail())
                            .password(password.getPassword())
                            .authorities(emptyList())
                            .build()
                    }
            }
    }
}
