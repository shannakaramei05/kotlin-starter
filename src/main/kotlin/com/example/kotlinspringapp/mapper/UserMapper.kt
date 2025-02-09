package com.example.kotlinspringapp.mapper

import com.example.kotlinspringapp.dto.UnVerifyUserResponse
import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.extension.toUserResponseDTO
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

object UserMapper {

    fun toEntity(userDTO: UserRegisterDTO) : User {
        val bCryptPasswordEncoder = BCryptPasswordEncoder();
        return User(
            userId = userDTO.userId,
            fullName = userDTO.fullName,
            email = userDTO.email,
            password = bCryptPasswordEncoder.encode(userDTO.password),
            role = userDTO.role,
        )
    }

    fun toUnVerifyUser(user: User) : UnVerifyUserResponse {
        return user.toUserResponseDTO();
    }

    fun toUnVerifyUserList(users:List<User>) : List<UnVerifyUserResponse> {
        return users.map { it.toUserResponseDTO() }
    }
}