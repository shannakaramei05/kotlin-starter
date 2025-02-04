package com.example.kotlinspringapp.repositories

import com.example.kotlinspringapp.model.BookStocks
import org.springframework.data.jpa.repository.JpaRepository

interface BookStocksRepository :JpaRepository<BookStocks,Long> {
}