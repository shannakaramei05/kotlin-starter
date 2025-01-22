package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.dto.BookDTO
import com.example.kotlinspringapp.exceptions.BookAlreadyExistsException
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.services.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController (private val bookService: BookService){

    @GetMapping
    fun getAllBooks() : ResponseEntity<List<Book>> {
        val books = bookService.getAllBooks()
        return ResponseEntity(books, HttpStatus.OK)
    }

    @PostMapping
    fun addBook(@RequestBody bookDTO:BookDTO) : ResponseEntity<String> {
        return try{
            val savedBook = bookService.addBook(bookDTO);
            ResponseEntity("Book Added successfully with title: '${savedBook.title}'", HttpStatus.CREATED)
        }catch (e:BookAlreadyExistsException) {
            ResponseEntity(e.message,HttpStatus.CONFLICT)
        }

    }
}