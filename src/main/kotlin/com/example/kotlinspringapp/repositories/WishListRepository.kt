package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.WishListBook
import org.springframework.data.jpa.repository.JpaRepository

interface WishListRepository: JpaRepository<WishListBook, Long> {

    fun findByUserId(userId:Long) : List<WishListBook>
    fun deleteByIdAndUserId(wishId: Long, userId :Long): Int
}