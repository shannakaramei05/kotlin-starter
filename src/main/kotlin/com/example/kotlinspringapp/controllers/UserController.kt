package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.dto.RemoveWishListBookDTO
import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.dto.VoteRequestDTO
import com.example.kotlinspringapp.dto.WishListBookRequestDTO
import com.example.kotlinspringapp.exceptions.UserAlreadyExists
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.services.BookService
import com.example.kotlinspringapp.services.UserService
import com.example.kotlinspringapp.services.VoteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService,
    private val voteService: VoteService,
    private val bookService: BookService){

    @PostMapping("/register")
    fun registerUser(@RequestBody userRequest: UserRegisterDTO) : ResponseEntity<String> {
       return try {
           val user = userService.registerUser(userRequest)
           ResponseEntity("'${user.userId}' successfully register. please do verify email", HttpStatus.CREATED)
       }catch (e:UserAlreadyExists) {
           ResponseEntity(e.message,HttpStatus.BAD_REQUEST)
       }
    }

    @GetMapping
    fun retrieveUser():ResponseEntity<List<User>> {
        val users = userService.retrieveUser()
        return ResponseEntity(users,HttpStatus.OK)
    }

    @PostMapping("/add-wishlist")
    fun addWishListBook(@RequestBody request:WishListBookRequestDTO) : ResponseEntity<String> {
        val wishBook = bookService.addWishListBook(request)
        return ResponseEntity("'${wishBook.bookTitle} added to Wishlist", HttpStatus.OK)
    }

    @GetMapping("/{userId}/wishlists")
    fun getUserWishListBook(@PathVariable userId:String) : ResponseEntity<List<String>> {
        val wishListBook = userService.getWishListBook(userId)
        return ResponseEntity(wishListBook, HttpStatus.OK )
    }

    @DeleteMapping("/remove/wishlist")
    fun removeWishListBook (@RequestBody request:RemoveWishListBookDTO) : ResponseEntity<String> {
        val isRemoved = userService.removeWishListBook(request)
        return if(isRemoved) {
            ResponseEntity.ok("Book with title '${request.bookTitle}' removed from user wishlist.")
        }else{
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with title '${request.bookTitle}' not found in wishlist for user ${request.userId}.")
        }
    }


//    @PostMapping("/vote")
//    fun doVote(@RequestBody request:VoteRequestDTO) :ResponseEntity<String> {
//        val vote = voteService.doVote(request)
//        return ResponseEntity(vote, HttpStatus.ACCEPTED)
//    }
}