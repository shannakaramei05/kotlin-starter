package com.example.kotlinspringapp.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.security.SignatureException

@ControllerAdvice
class JwtContollerAdvice {
    @ExceptionHandler(value = [ExpiredJwtException::class, AuthenticationException::class, SignatureException::class])
    fun handleAuthenticationExceptions(ex:RuntimeException) : ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body("Authentication faied : ${ex.message}")
    }
}