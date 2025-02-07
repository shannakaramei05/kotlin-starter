package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.security.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController (
    private val authenticationService:AuthenticationService
){

    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest:AuthenticationRequest) :AuthenticationResponse = authenticationService.authentication(authRequest)

    @PostMapping("/refresh/token")
    fun refreshAccessToken(@RequestBody request:RefreshTokenRequest) :TokenResponse = TokenResponse(token = authenticationService.refreshAccessToken(request.token))
}