package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.BookResponseDTO
import com.example.kotlinspringapp.dto.WishListBookRequestDTO
import com.example.kotlinspringapp.mapper.BookMapper
import com.example.kotlinspringapp.model.Author
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.model.WishListBook
import com.example.kotlinspringapp.repositories.AuthorRepository
import com.example.kotlinspringapp.repositories.BookRepository
import com.example.kotlinspringapp.repositories.UserRepository
import com.example.kotlinspringapp.repositories.WishListRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BookService (
    private  val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
    private val wishListRepository: WishListRepository,
    private val userRepository: UserRepository
    ) {

    fun getAllBooks() :List<BookResponseDTO> {
        return bookRepository.findAllWithAuthorFullName()
    }

    @Transactional
    fun addBook(bookResponseDTO: BookResponseDTO) :  Book{

        val authorName = bookResponseDTO.author?:"";
        var author = authorRepository.findByFullName(authorName)
        if(author == null) {
            author = Author(name = authorName)
            authorRepository.save(author)
        }

//        bookRepository.findByTitle(bookDTO.title)?.let{
//            throw BookAlreadyExistsException("A Book with the title '${bookDTO.title}' already exists.")
//        }

        val book = BookMapper.toEntity(bookResponseDTO,author)
        author.books.add(book)

        return bookRepository.save(book)
    }

    @Transactional
    fun addWishListBook(request:WishListBookRequestDTO) : WishListBook {
        val user = userRepository.findByUserId(request.userId)
        val wishList = BookMapper.toWishListEntity(request,user)
        user.wishlistBook.add(wishList)
        return wishListRepository.save(wishList)
    }


}