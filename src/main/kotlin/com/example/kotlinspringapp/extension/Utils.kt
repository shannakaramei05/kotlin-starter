package com.example.kotlinspringapp.extension

import com.example.kotlinspringapp.dto.UnVerifyUserResponse
import com.example.kotlinspringapp.model.User

fun User.toUserResponseDTO(): UnVerifyUserResponse {
    return UnVerifyUserResponse(
        userId = this.userId,
        fullName = this.fullName,
        role = this.role,
        isVerify = this.isVerify
    )
}

