package com.example.kotlinspringapp.dto

data class VoteRequestDTO(
    val userId:String,
    val bookId:Long,
    val rating:Int,
    val comment:String
)
