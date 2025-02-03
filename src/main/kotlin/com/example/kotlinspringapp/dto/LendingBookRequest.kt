package com.example.kotlinspringapp.dto

import java.time.LocalDateTime

data class LendingBookRequest(
    val bookId: Long,
    val bookTitle:String,
    val userId :String,
    val dayOfLending: Long,
)
