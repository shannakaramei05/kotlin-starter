package com.example.kotlinspringapp.dto

import com.example.kotlinspringapp.constan.Role

data class UserRegisterDTO(
    val userId : String,
    val fullName:String,
    val email:String,
    val password:String,
    val role:Role
)
