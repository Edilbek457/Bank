package org.example.gateway.exception.user

open class UserNotFoundException(message: String) : RuntimeException(message) {

    class ByEmail(email: String) :
        UserNotFoundException("User not found with email: $email")

    class ById(id: Long) :
        UserNotFoundException("User not found with id: $id")

    class ByPhoneNumber(phoneNumber: Long) :
        UserNotFoundException("User not found with phone number: $phoneNumber")
}