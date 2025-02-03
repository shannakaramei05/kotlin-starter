package com.example.kotlinspringapp.dto

import java.time.LocalDateTime

data class LendingBookResponse(
    val bookTitle:String,
    val dayOfLending:Long,
    val returnDate:LocalDateTime,
    val isApprove: Boolean = false
)
