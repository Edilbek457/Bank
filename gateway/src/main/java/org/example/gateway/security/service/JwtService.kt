package org.example.gateway.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.example.gateway.exception.user.UserNotFoundException
import org.example.gateway.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*
import javax.crypto.SecretKey


@Service
class JwtService (
    private val userRepository: UserRepository
) {

    //В будущем уберу
    private val SECRET_KEY = "4F635166546A576E5A7234753778214125442A462D4A614E645267556B587032"

    private fun getSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())
    }

    fun generateAccessToken(userDetails: UserDetails): Mono<String> {
        return userRepository.findByEmail(userDetails.username)
            .switchIfEmpty(Mono.error(UserNotFoundException.ByEmail(userDetails.username)))
            .map { user ->
                val userId = user.getId()

                Jwts.builder()
                    .claims()
                    .add("userId", userId)
                    .and()
                    .issuer("Bank_gateway_service")
                    .issuedAt(Date())
                    .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 15))
                    .signWith(getSigningKey())
                    .compact()
            }
    }

    fun generateRefreshToken(userDetails: UserDetails): Mono<String> {
        return userRepository.findByEmail(userDetails.username)
            .switchIfEmpty(Mono.error(UserNotFoundException.ByEmail(userDetails.username)))
            .map { user ->
                val userId = user.getId()
                val email = user.getEmail()

                Jwts.builder()
                    .claims()
                    .add("userId", userId)
                    .add("email", email)
                    .and()
                    .issuer("Bank_gateway_service")
                    .issuedAt(Date())
                    .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                    .signWith(getSigningKey())
                    .compact()
            }
    }

    fun extractUserId(token: String): String {
        return extractAllClaims(token)["userId"].toString()
    }

    fun extractEmail(token: String): String {
        return extractAllClaims(token)["email"].toString()
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun tokenIsValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return email == userDetails.username && !tokenIsExpired(token)
    }

    private fun tokenIsExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }
}


