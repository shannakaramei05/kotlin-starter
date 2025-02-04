package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.Lending
import org.springframework.data.jpa.repository.JpaRepository

interface LendingRepository : JpaRepository<Lending, Long>{

    fun findByIsApproveFalse() :List<Lending>
}