package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.WishListBook
import org.springframework.data.jpa.repository.JpaRepository

interface WishListRepository: JpaRepository<WishListBook, Long> {
}