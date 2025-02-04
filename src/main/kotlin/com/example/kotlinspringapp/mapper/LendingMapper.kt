package com.example.kotlinspringapp.mapper

import com.example.kotlinspringapp.dto.LendingBookRequest
import com.example.kotlinspringapp.dto.LendingBookResponse
import java.time.LocalDateTime

object LendingMapper {

    fun toLendingResponsse(request:LendingBookRequest, title:String) :LendingBookResponse{
        return LendingBookResponse(
            bookTitle = title,
            dayOfLending = request.dayOfLending,
            returnDate = LocalDateTime.now().plusDays(request.dayOfLending)
        )
    }
}