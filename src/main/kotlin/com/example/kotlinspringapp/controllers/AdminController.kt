package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.dto.UnVerifyUserResponse
import com.example.kotlinspringapp.dto.ActivateUserRequest
import com.example.kotlinspringapp.dto.ActivateUserResponse
import com.example.kotlinspringapp.services.UserService
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
    private val userService: UserService
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
}