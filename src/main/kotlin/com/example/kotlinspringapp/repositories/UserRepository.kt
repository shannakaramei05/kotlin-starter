package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long> {
}