package com.example.kotlinspringapp.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
data class BookStocks(

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val sequenceNo:Long = 0,
    val totalCopies:Int,
    var availableCopies: Int? = 0,
    val isAvailable:Boolean = true,

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    val book : Book,


    )
