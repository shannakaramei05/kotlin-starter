package com.example.kotlinspringapp.mapper

import com.example.kotlinspringapp.dto.BookResponseDTO
import com.example.kotlinspringapp.model.Author
import com.example.kotlinspringapp.model.Book

object BookMapper {
   fun toDTO(book: Book) : BookResponseDTO {
       return BookResponseDTO(
           title=book.title,
           publishYear = book.publishYear,
           bookCoverUrl = book.bookCoverUrl,
           author  = book.author?.fullName ?: ""
       )
   }

    fun toEntity(bookResponseDTO: BookResponseDTO, author:Author) :Book {
        return Book(
            title = bookResponseDTO.title,
            publishYear = bookResponseDTO.publishYear,
            bookCoverUrl = bookResponseDTO.bookCoverUrl,
            author = author
        )
    }
}