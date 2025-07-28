package org.example.gateway.repository

import org.example.gateway.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {
    fun findByEmail(email: String): Mono<User>
    fun getByEmail(email: String): Mono<User?>
    fun getByUserId(id: Long): Mono<User?>
}