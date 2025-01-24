package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.model.Vote
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository:JpaRepository<Vote,Long> {

    fun findByUserAndBook(user:User, book:Book) : Vote?
}