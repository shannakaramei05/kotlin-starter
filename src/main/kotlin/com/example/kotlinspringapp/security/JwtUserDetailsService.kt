package com.example.kotlinspringapp.security

import com.example.kotlinspringapp.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class JwtUserDetailsService(private val userRepository: UserRepository) :UserDetailsService{
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("User $userId not Found")

        return User.builder()
            .username(user.get().userId)
            .password(user.get().email)
            .roles(user.get().role.toString())
            .build()
    }

}