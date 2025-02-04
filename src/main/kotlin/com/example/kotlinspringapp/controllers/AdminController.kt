package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.dto.*
import com.example.kotlinspringapp.model.Lending
import com.example.kotlinspringapp.services.BookService
import com.example.kotlinspringapp.services.LendingService
import com.example.kotlinspringapp.services.UserService
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/admin")
class AdminController (
    private val userService: UserService,
    private val bookService: BookService,
    private val lendingService: LendingService
) {

    @GetMapping("/get-new-users")
    fun getNewUser() :ResponseEntity<List<UnVerifyUserResponse>>{
        return ResponseEntity(userService.getUnVerifyUser(),HttpStatus.OK)
    }

    @PostMapping("/verify/user")
    fun verifyUser(@RequestBody request:ActivateUserRequest) : ResponseEntity<ActivateUserResponse> {
        val activateUser = userService.activateUser(request)
        return ResponseEntity(activateUser, HttpStatus.OK)
    }

    @PostMapping("/add/stocks/book")
    fun addStocks(@RequestBody request: AddStocksBookRequest) : ResponseEntity<String> {
        val stock = bookService.addStockBook(request);
        return ResponseEntity.ok(
            "Book Stock Successfully Add."
        )
    }

    @GetMapping("/books/request")
    fun getAllRequestBook() : ResponseEntity<List<Lending>> {
        return ResponseEntity(lendingService.getListRequest(), HttpStatus.OK)
    }

    @PostMapping("/books/approve")
    fun apprvLendBook(@RequestBody request:ApprovalLendingRequest) : ResponseEntity<ApprovalLendingResponse> {
        return ResponseEntity(
            lendingService.doApprovalLending(request),
            HttpStatus.OK
        )
    }
}