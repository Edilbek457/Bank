package org.example.gateway.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.springframework.data.annotation.Id
import java.time.LocalDate

@Entity
class User(
    @Id
    val id: Long,

    @Column(unique = true, nullable = false, name = "email")
    val email: String,

    @Column(unique = true, nullable = false, name = "phone_number")
    val phoneNumber: String?,

    @Column(nullable = false, name = "user_first_name")
    val userFirstName: String,

    @Column(nullable = false, name = "user_last_name")
    val userLastName: String,

    @Column(nullable = false, name = "last_active_time")
    val lastActiveTime: LocalDate,

    @Column(nullable = false, name = "create_time")
    val createTime : LocalDate,

    @Column(nullable = false, name = "update_time")
    val updateTime : LocalDate
)
