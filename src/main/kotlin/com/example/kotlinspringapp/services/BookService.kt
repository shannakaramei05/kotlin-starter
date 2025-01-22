package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.BookDTO
import com.example.kotlinspringapp.exceptions.BookAlreadyExistsException
import com.example.kotlinspringapp.mapper.BookMapper
import com.example.kotlinspringapp.model.Author
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.repositories.AuthorRepository
import com.example.kotlinspringapp.repositories.BookRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BookService (
    private  val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
    ) {

    fun getAllBooks() :List<Book> {
        return bookRepository.findAll()
    }

    @Transactional
    fun addBook(bookDTO: BookDTO) :  Book{

        val authorName = bookDTO.author?:"";
        var author = authorRepository.findByFullName(authorName)
        if(author == null) {
            author = Author(name = authorName)
            authorRepository.save(author)
        }

//        bookRepository.findByTitle(bookDTO.title)?.let{
//            throw BookAlreadyExistsException("A Book with the title '${bookDTO.title}' already exists.")
//        }

        val book = BookMapper.toEntity(bookDTO,author)
        author.books.add(book)

        return bookRepository.save(book)
    }


}