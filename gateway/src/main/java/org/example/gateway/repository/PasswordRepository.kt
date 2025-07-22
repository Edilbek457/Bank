package org.example.gateway.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PasswordRepository: JpaRepository<Password, Long> {

}