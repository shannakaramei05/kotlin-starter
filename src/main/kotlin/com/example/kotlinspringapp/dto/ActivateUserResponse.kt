package com.example.kotlinspringapp.dto

data class ActivateUserResponse(
    val success:Boolean,
    val message:String,
    val activateUsers: List<String>,
    val failedUsers: Map<String, String>
)
