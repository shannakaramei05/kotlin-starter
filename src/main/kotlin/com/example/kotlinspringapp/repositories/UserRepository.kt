package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UserRepository : JpaRepository<User,Long> {
    fun findByUserId(userId:String) : Optional<User>
    fun findByIsVerifyFalse() : List<User>
    @Modifying
    @Query("UPDATE User u SET u.isVerify = true WHERE u.userId IN :userIds")
    fun activateUsersByIds(@Param("userIds") userIds: List<String>): Int
}