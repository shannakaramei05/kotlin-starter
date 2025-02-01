package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User,Long> {
    fun findByUserId(userId:String) : Optional<User>
}