package com.example.kotlinspringapp.model

import com.example.kotlinspringapp.constan.Role
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    val userId: String,
    val fullName: String,
    val email: String,
    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER,

    val isVerify:Boolean = false,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val vote: MutableList<Vote> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val wishlistBook: MutableList<WishListBook> = mutableListOf()
)
