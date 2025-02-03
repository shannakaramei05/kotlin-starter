package com.example.kotlinspringapp.dto

data class AddStocksBookRequest(
    val bookId : Long,
    val totalCopies:Int,
    val availableCopes:Int
)
