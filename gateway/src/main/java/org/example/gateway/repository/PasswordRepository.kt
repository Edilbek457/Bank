package org.example.gateway.repository

import org.example.gateway.model.Password
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PasswordRepository: ReactiveCrudRepository<Password, Long> {

}