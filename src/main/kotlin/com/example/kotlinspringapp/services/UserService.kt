package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.mapper.UserMapper
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository){

    fun registerUser(request: UserRegisterDTO) : User {
        return userRepository.save(UserMapper.toEntity(request))
    }

    fun retrieveUser(): List<User>{
        return userRepository.findAll();
    }

}