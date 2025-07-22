package org.example.gateway.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.example.gateway.exception.user.UserNotFoundException
import org.example.gateway.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService (
    private val userRepository: UserRepository
) {

    //В будущем уберу в env файл
    val SECRET_KEY = "4F635166546A576E5A7234753778214125442A462D4A614E645267556B587032"

    private fun getSigningKey() = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())

    fun generateAccessToken(userDetails: UserDetails): String {
        val user = userRepository.findByEmail(userDetails.username)
            ?: throw UserNotFoundException.ByEmail(userDetails.username)

        val userId: Long = user.getId()

        return Jwts.builder()
            .claims()
            .add("userId", userId)
            .and()
            .issuer("Bank_gateway_service")
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 минут
            .signWith(getSignInKey())
            .compact()
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        val user = userRepository.findByEmail(userDetails.username)
            ?: throw UserNotFoundException.ByEmail(userDetails.username)

        val userId: Long = user.getId()

        return Jwts.builder()
            .claims()
            .add("userId", userId)
            .add("email", user.getEmail())
            .and()
            .issuer("Bank_gateway_service")
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 дней
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .compact()
    }

    fun getSignInKey() : Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))
    }

    fun extractUserId(token: String): String {
        return extractAllClaims(token)["userId"] as String
    }

    fun extractEmail(token: String): String {
        return extractAllClaims(token)["email"] as String
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
        val email: String = extractEmail(token)
        return email == userDetails.username && !tokenIsExpired(token)
    }

    private fun tokenIsExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

}


