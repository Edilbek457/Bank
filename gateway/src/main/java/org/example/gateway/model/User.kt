package org.example.gateway.model

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Table("users")
data class User(

    @Id
    val id: Long? = null,

    @Column("email")
    val email: String,

    @Column("phone_number")
    val phoneNumber: Long?,

    @Column("user_first_name")
    val userFirstName: String,

    @Column("user_last_name")
    val userLastName: String,

    @Column("last_active_time")
    val lastActiveTime: LocalDate,

    @Column("create_time")
    val createTime: LocalDate,

    @Column("update_time")
    val updateTime: LocalDate
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