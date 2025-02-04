package com.example.kotlinspringapp.dto

import com.example.kotlinspringapp.constan.Role

data class UnVerifyUserResponse(
    val userId:String,
    val fullName:String,
    val role:Role,
    val isVerify:Boolean
)
