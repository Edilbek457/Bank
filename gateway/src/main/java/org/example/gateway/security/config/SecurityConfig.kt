package org.example.gateway.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
open class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val userDetailsService: ReactiveUserDetailsService
) {

    @Bean
    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/auth/login", "/auth/register").permitAll()
                it.anyExchange().authenticated()
            }
            .authenticationManager(authenticationManager())
            .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return Argon2PasswordEncoder(16, 32, 1, 65536, 4)
    }

    @Bean
    open fun authenticationManager(): ReactiveAuthenticationManager {
        return CustomReactiveAuthenticationManager(userDetailsService, passwordEncoder())
    }
}


