package org.example.gateway.model

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "users")
data class User(

    @Id
    private var id: Long,

    @Column("email")
    private var email: String,

    @Column("phone_number")
    private var phoneNumber: Long?,

    @Column("user_first_name")
    private var userFirstName: String,

    @Column("user_last_name")
    private var userLastName: String,

    @Column("last_active_time")
    private var lastActiveTime: LocalDate,

    @Column("create_time")
    private var createTime: LocalDate,

    @Column("update_time")
    private var updateTime: LocalDate
) {
    fun getId(): Long = id
    fun getEmail(): String = email
    fun setEmail(newEmail: String) { this.email = newEmail }
    fun getPhoneNumber(): Long? = phoneNumber
    fun setPhoneNumber(newPhone: Long?) { this.phoneNumber = newPhone }
    fun getUserFirstName(): String = userFirstName
    fun setUserFirstName(userFirstName: String) { this.userFirstName = userFirstName }
    fun getUserLastName(): String = userLastName
    fun setUserLastName(userLastName: String) { this.userLastName = userLastName }
    fun getLastActiveTime(): LocalDate = lastActiveTime
    fun setLastActiveTime(newLastActiveTime: LocalDate) { this.lastActiveTime = newLastActiveTime }
    fun getCreateTime(): LocalDate? = createTime
    fun setCreateTime(newCreateTime: LocalDate) { this.createTime = newCreateTime }
    fun getUpdateTime(): LocalDate = updateTime
    fun setUpdateTime(newUpdateTime: LocalDate) { this.updateTime = newUpdateTime }
}