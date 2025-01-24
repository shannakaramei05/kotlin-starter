package com.example.kotlinspringapp.mapper

import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.model.User

object UserMapper {

    fun toEntity(userDTO: UserRegisterDTO) : User {
        return User(
            userId = userDTO.userId,
            fullName = userDTO.fullName,
            email = userDTO.email
        )
    }
}