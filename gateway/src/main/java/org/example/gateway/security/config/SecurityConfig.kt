package org.example.gateway.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers


@Configuration
@EnableWebFluxSecurity
open class SecurityConfig(
    private val customAuthenticationManager: ReactiveAuthenticationManager,
    private val jwtAuthFilter: JwtAuthFilter
) {

    @Bean
    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/auth/register", "/auth/verify-code", "/auth/login").permitAll()
                    .anyExchange().authenticated()
            }
            .authenticationManager(customAuthenticationManager)
            .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    open fun passwordEncoder(): Argon2PasswordEncoder {
        return Argon2PasswordEncoder(16, 32, 1, 65536, 4)
    }
}
