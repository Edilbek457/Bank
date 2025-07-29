package org.example.gateway.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.Id
import java.time.LocalDate

@Entity
@Table(name = "password")

class Password (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long,

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", nullable = false)
    private var user: User,

    @Column(nullable = false)
    private var password: String,

    @Column(nullable = false)
    private var passwordStrengthLevel: Byte,

    @Column(nullable = false, name = "create_time")
    private var createTime : LocalDate,

    @Column(nullable = false, name = "update_time")
    private var updateTime : LocalDate

) {
    fun getId(): Long = id
    fun setId(id: Long) { this.id = id }
    fun getPassword(): String = password
    fun getPasswordStrengthLevel(): Byte = passwordStrengthLevel
    fun setPasswordStrengthLevel(passwordStrengthLevel: Byte) { this.passwordStrengthLevel = passwordStrengthLevel }
    fun getCreateTime(): LocalDate = createTime
    fun getUpdateTime(): LocalDate = updateTime
    fun setUpdateTime(updateTime: LocalDate) { this.updateTime = updateTime }
}

