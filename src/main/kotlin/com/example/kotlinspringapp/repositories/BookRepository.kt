package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.dto.BookResponseDTO
import com.example.kotlinspringapp.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book,Long> {
    fun findByTitle(title:String) :Book?

    @Query(
      """
            SELECT new com.example.kotlinspringapp.dto.BookResponseDTO(
            b.title,
            b.publishYear,
            b.bookCoverUrl,
            a.fullName
        )
        FROM Book b
        JOIN b.author a
      """
    )
    fun findAllWithAuthorFullName(): List<BookResponseDTO>
}