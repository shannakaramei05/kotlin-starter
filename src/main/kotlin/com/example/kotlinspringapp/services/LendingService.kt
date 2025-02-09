package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.ApprovalLendingRequest
import com.example.kotlinspringapp.dto.ApprovalLendingResponse
import com.example.kotlinspringapp.dto.LendingBookRequest
import com.example.kotlinspringapp.dto.LendingBookResponse
import com.example.kotlinspringapp.exceptions.BookNotAvailable
import com.example.kotlinspringapp.exceptions.BookNotFound
import com.example.kotlinspringapp.exceptions.RequestNotValid
import com.example.kotlinspringapp.exceptions.UserNotFound
import com.example.kotlinspringapp.mapper.LendingMapper
import com.example.kotlinspringapp.model.Lending
import com.example.kotlinspringapp.repositories.BookRepository
import com.example.kotlinspringapp.repositories.BookStocksRepository
import com.example.kotlinspringapp.repositories.LendingRepository
import com.example.kotlinspringapp.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class LendingService (
    private val lendingRepository: LendingRepository,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val bookStocksRepository: BookStocksRepository
    ) {

    fun requestLendBook(request:LendingBookRequest) : LendingBookResponse{
        val book = bookRepository.findById(request.bookId).orElseThrow { throw BookNotFound("Book Not Found") }
        //checking availableCopies
        val availableCopies = book.bookStocks?.availableCopies;

        //checking user is member or not
        val user = userRepository.findByUserId(request.userId).orElseThrow{throw UserNotFound("User Not Found!")}
        if(!user.isVerify) {
            throw RequestNotValid("user is not membership, wait admin to approve!!")
        }

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

    fun getListRequest() : List<Lending> {
        return lendingRepository.findByIsApproveFalse();
    }

    @Transactional
    fun doApprovalLending(request:ApprovalLendingRequest) : ApprovalLendingResponse {
        val lending = lendingRepository.findById(request.lendingId).orElseThrow { throw RequestNotValid("Lending request not found") }
        val book = bookRepository.findById(lending.bookId).orElseThrow { throw BookNotFound("Book Not Found") }
        val bookStocks = book.bookStocks ?: throw RequestNotValid("Book stock information is missing")

        if (bookStocks.availableCopies <= 0) {
            throw RequestNotValid("Request cannot be approved due to insufficient stock. Please try again later")
        }

        lending.isApprove = true;
        bookStocks.availableCopies -= 1;

        if(bookStocks.availableCopies == 0) {
            bookStocks.isAvailable = false
        }

        book.bookStocks = bookStocks;
        lendingRepository.save(lending)
        bookRepository.save(book)

        return ApprovalLendingResponse(
            message = "Request Fully Approve",
            bookTitle = book.title,
            daysOfLending =ChronoUnit.DAYS.between(lending.borrowDate, lending.dueDate),
            dueDate = lending.dueDate)

    }
}