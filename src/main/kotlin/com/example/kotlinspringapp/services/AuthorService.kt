package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.model.Author
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.repositories.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
) {

    fun getBookByAuthorId(id:Long): MutableList<Book> {
//        authorRepository.findById(id)?.let{
//            throw AuthorNotExistException("Author with name '${id}' is not exists, please check author name again!!")
//        }
        return authorRepository.findById(id).get().books;
    }

    fun getListAuthor () : List<Author> {
        return authorRepository.findAll();
    }

}