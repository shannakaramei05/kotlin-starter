package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.RemoveWishListBookDTO
import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.exceptions.UserAlreadyExists
import com.example.kotlinspringapp.exceptions.UserNotFound
import com.example.kotlinspringapp.mapper.BookMapper
import com.example.kotlinspringapp.mapper.UserMapper
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.repositories.UserRepository
import com.example.kotlinspringapp.repositories.VoteRepository
import com.example.kotlinspringapp.repositories.WishListRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val wishListRepository: WishListRepository){

    fun registerUser(request: UserRegisterDTO) : User {
        val user = userRepository.findByUserId(request.userId);
        if(!user.isPresent){
            return userRepository.save(UserMapper.toEntity(request))
        }
        throw UserAlreadyExists("User Already Exist")
    }

    fun retrieveUser(): List<User>{
        return userRepository.findAll();
    }

    fun getWishListBook(userId:String) : List<String> {
        val user = userRepository.findByUserId(userId).orElseThrow { throw UserNotFound("User Not Found, Please Check Again USER ID") }
        return BookMapper.wishListToString(user.wishlistBook)
    }

    @Transactional
    fun removeWishListBook(request:RemoveWishListBookDTO) : Boolean {
       val deleteCount = wishListRepository.deleteByIdAndUserId(request.wishListId,request.userId)
        return deleteCount>0
    }

}