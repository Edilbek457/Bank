package org.example.gateway.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @Column(unique = true, nullable = false, name = "email")
    private var email: String,

    @Column(unique = true, nullable = false, name = "phone_number")
    private var phoneNumber: Long?,

    @Column(nullable = false, name = "user_first_name")
    private var userFirstName: String,

    @Column(nullable = false, name = "user_last_name")
    private var userLastName: String,

    @Column(nullable = false, name = "last_active_time")
    private var lastActiveTime: LocalDate,

    @Column(nullable = false, name = "create_time")
    private var createTime : LocalDate,

    @Column(nullable = false, name = "update_time")
    private var updateTime : LocalDate
) {
    fun getId(): Long = id

    fun getEmail(): String = email

    fun setEmail(newEmail: String) {
        this.email = newEmail
    }

    fun getPhoneNumber(): Long? = phoneNumber

    fun setPhoneNumber(newPhone: Long?) {
        this.phoneNumber = newPhone
    }

    fun getUserFirstName(): String = userFirstName

    fun setUserFirstName(userFirstName: String) {
        this.userFirstName = userFirstName
    }

    fun getUserLastName(): String = userLastName

    fun setUserLastName(userLastName: String) {
        this.userLastName = userLastName
    }

    fun getLastActiveTime(): LocalDate = lastActiveTime

    fun setLastActiveTime(newLastActiveTime: LocalDate) {
        this.lastActiveTime = newLastActiveTime
    }

    fun getCreateTime(): LocalDate? = createTime

    fun setCreateTime(newCreateTime: LocalDate) {
        this.createTime = newCreateTime
    }

    fun getUpdateTime(): LocalDate = updateTime

    fun setUpdateTime(newUpdateTime: LocalDate) {
        this.updateTime = newUpdateTime
    }
}