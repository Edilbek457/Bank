package org.example.gateway.security.config

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomReactiveAuthenticationManager(
    private val userDetailsService: ReactiveUserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val username = authentication.name
        val password = authentication.credentials.toString()

        return userDetailsService.findByUsername(username)
            .filter { passwordEncoder.matches(password, it.password) }
            .map { user ->
                UsernamePasswordAuthenticationToken(user, null, user.authorities)
            }
    }
}
