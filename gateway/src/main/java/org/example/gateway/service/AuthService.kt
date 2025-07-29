package org.example.gateway.service

import org.example.gateway.model.User
import org.springframework.stereotype.Service


@Service
class AuthService (
    private val userService: UserService,
    private val passwordService: PasswordService
) {

}