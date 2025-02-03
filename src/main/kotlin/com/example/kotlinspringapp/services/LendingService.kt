package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.LendingBookRequest
import com.example.kotlinspringapp.dto.LendingBookResponse
import com.example.kotlinspringapp.exceptions.BookNotAvailable
import com.example.kotlinspringapp.exceptions.BookNotFound
import com.example.kotlinspringapp.mapper.LendingMapper
import com.example.kotlinspringapp.model.Lending
import com.example.kotlinspringapp.repositories.BookRepository
import com.example.kotlinspringapp.repositories.LendingRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LendingService (
    private val lendingRepository: LendingRepository,
    private val bookRepository: BookRepository
    ) {

    fun requestLendBook(request:LendingBookRequest) : LendingBookResponse{
        val book = bookRepository.findById(request.bookId).orElseThrow { throw BookNotFound("Book Not Found") }
        //checking availableCopies
        val availableCopies = book.bookStocks?.availableCopies;

        val requestLending = Lending(
            bookId =  request.bookId,
            borrowDate = LocalDateTime.now(),
            userId = request.userId,
            dueDate = LocalDateTime.now().plusDays(request.dayOfLending)
        )

        if (availableCopies != null) {
            if(availableCopies > 0) {
                lendingRepository.save(requestLending)
                return LendingMapper.toLendingResponsse(request, book.title)
            }
        }

        throw BookNotAvailable("Book Not Available")
    }
}