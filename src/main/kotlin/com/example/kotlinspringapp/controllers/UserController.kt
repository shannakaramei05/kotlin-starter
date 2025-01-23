package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.exceptions.UserAlreadyExists
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.services.UserService
import com.example.kotlinspringapp.services.VoteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService,
    private val voteService: VoteService){

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
}