package com.example.kotlinspringapp.dto

data class RemoveWishListBookDTO(
    val userId:Long,
    val wishListId:Long,
    val bookTitle:String
)
