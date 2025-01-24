package com.example.kotlinspringapp.mapper

import com.example.kotlinspringapp.dto.VoteRequestDTO
import com.example.kotlinspringapp.model.Book
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.model.Vote

object VoteMapper {

   fun toEntity(voteDTO:VoteRequestDTO, user:User, book:Book) : Vote {
       return Vote(
           user = user,
           book = book,
           rating = voteDTO.rating,
           comment = voteDTO.comment
       )
   }
}