package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.RemoveWishListBookDTO
import com.example.kotlinspringapp.dto.UserRegisterDTO
import com.example.kotlinspringapp.mapper.BookMapper
import com.example.kotlinspringapp.mapper.UserMapper
import com.example.kotlinspringapp.model.User
import com.example.kotlinspringapp.repositories.UserRepository
import com.example.kotlinspringapp.repositories.WishListRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val wishListRepository: WishListRepository){

    fun registerUser(request: UserRegisterDTO) : User {
        return userRepository.save(UserMapper.toEntity(request))
    }

    fun retrieveUser(): List<User>{
        return userRepository.findAll();
    }

    fun getWishListBook(userId:String) : List<String> {
        val user = userRepository.findByUserId(userId)
        return BookMapper.wishListToString(user.wishlistBook)
    }

    @Transactional
    fun removeWishListBook(request:RemoveWishListBookDTO) : Boolean {
       val deleteCount = wishListRepository.deleteByIdAndUserId(request.wishListId,request.userId)
        return deleteCount>0
    }

}