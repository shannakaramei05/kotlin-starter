package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book,Long> {
    fun findByTitle(title:String) :Book?
}