package com.example.kotlinspringapp.mapper

import com.example.kotlinspringapp.dto.BookDTO
import com.example.kotlinspringapp.model.Author
import com.example.kotlinspringapp.model.Book

object BookMapper {
   fun toDTO(book: Book) : BookDTO {
       return BookDTO(
           title=book.title,
           publishYear = book.publishYear,
           bookCoverUrl = book.bookCoverUrl,
           author = book.author?.fullName
       )
   }

    fun toEntity(bookDTO: BookDTO, author:Author) :Book {
        return Book(
            title = bookDTO.title,
            publishYear = bookDTO.publishYear,
            bookCoverUrl = bookDTO.bookCoverUrl,
            author = author
        )
    }
}