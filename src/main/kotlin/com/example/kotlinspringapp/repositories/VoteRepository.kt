package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.Vote
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository:JpaRepository<Vote,Long> {
}