package org.example.gateway.security.config

import org.example.gateway.security.service.CustomUserDetailsService
import org.example.gateway.security.service.JwtService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userDetailsService: CustomUserDetailsService
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val authHeader = exchange.request.headers.getFirst("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange)
        }

        val jwt = authHeader.substring(7)
        val email = jwtService.extractEmail(jwt)

        if (email == null) {
            return chain.filter(exchange)
        }

        return userDetailsService.findByUsername(email)
            .filter { jwtService.tokenIsValid(jwt, it) }
            .flatMap { userDetails ->
                val auth = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )

                chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth))
            }
    }
}
