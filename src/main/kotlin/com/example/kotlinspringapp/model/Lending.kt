package com.example.kotlinspringapp.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Lending(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    val userId: String,
    val bookId: Long,
    val borrowDate:LocalDateTime,
    val dueDate:LocalDateTime,
    val returnDate:LocalDateTime ? = null,
    val isApprove:Boolean= false

)
