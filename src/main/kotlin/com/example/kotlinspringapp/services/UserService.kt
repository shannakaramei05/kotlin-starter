package com.example.kotlinspringapp.services

import com.example.kotlinspringapp.dto.*
import com.example.kotlinspringapp.exceptions.UserAlreadyExists
import com.example.kotlinspringapp.exceptions.UserNotFound
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


    fun getUnVerifyUser() : List<UnVerifyUserResponse> {
        return UserMapper.toUnVerifyUserList(userRepository.findByIsVerifyFalse());
    }

    @Transactional
    fun activateUser(request:ActivateUserRequest) :ActivateUserResponse {
        require(request.userId.isNotEmpty()) {
            "Please select the userId you want to activate"
        }
        val activatedCount = userRepository.activateUsersByIds(request.userId)
        return ActivateUserResponse(
            success = true,
            message = "$activatedCount users activated successfully",
            activateUsers = request.userId,
            failedUsers = emptyMap()
        )

    }
}