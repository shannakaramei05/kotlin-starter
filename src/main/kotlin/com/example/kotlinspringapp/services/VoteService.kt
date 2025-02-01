package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.VoteRequestDTO
import com.example.kotlinspringapp.exceptions.BookNotFound
import com.example.kotlinspringapp.mapper.VoteMapper
import com.example.kotlinspringapp.repositories.BookRepository
import com.example.kotlinspringapp.repositories.UserRepository
import com.example.kotlinspringapp.repositories.VoteRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class VoteService (
    private val voteRepository: VoteRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
){

//    @Transactional
//    fun doVote(request:VoteRequestDTO) : String {
//        val user = userRepository.findByUserId(request.userId)
//        val book = bookRepository.findById(request.bookId).orElseThrow{BookNotFound("Book with ID ${request.bookId} not found!")}
//
//        val existingVote = voteRepository.findByUserAndBook(user,book)
//
//
//        return if( existingVote !=null) {
//            val oldRating = book.averageRating * book.totalVotes
//            book.averageRating = (oldRating - existingVote.rating + request.rating)/book.totalVotes
//            bookRepository.save(book)
//
//            existingVote.rating= request.rating
//            existingVote.comment = request.comment
//            voteRepository.save(existingVote)
//            "Vote Updated successfully"
//
//        }else{
//            val vote = VoteMapper.toEntity(request ,user,book);
//            voteRepository.save(vote)
//
//            book.totalVotes += 1
//            book.averageRating = ((book.averageRating * (book.totalVotes - 1)) + request.rating) / book.totalVotes
//            "Vote Already Submitted successfully"
//        }
//
//    }
//


}