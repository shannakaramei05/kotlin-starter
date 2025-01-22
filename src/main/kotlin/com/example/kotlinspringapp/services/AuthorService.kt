package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.repositories.AuthorRepository
import com.example.kotlinspringapp.repositories.BookRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository
) {


}