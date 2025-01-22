package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository:JpaRepository<Author,Long> {

    fun findByFullName(fullName:String):Author?
}