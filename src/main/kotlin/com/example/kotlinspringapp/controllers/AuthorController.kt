package com.example.kotlinspringapp.controllers

import com.example.kotlinspringapp.model.Author
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.services.AuthorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
class AuthorController (private val authorService: AuthorService){

    @GetMapping
    fun getAllAuthor():List<Author> {
        return authorService.getListAuthor();
    }

    @GetMapping("/{id}/books")
    fun getBookById(@PathVariable  id:Long) : List<Book> {
        return authorService.getBookByAuthorId(id)
    }


}