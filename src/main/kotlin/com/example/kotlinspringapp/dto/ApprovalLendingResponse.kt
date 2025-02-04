package com.example.kotlinspringapp.dto

import java.time.LocalDateTime

data class ApprovalLendingResponse(
    val message:String,
    val bookTitle:String,
    val daysOfLending:Long,
    val dueDate:LocalDateTime
)
