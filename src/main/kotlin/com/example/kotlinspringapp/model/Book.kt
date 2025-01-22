package com.example.kotlinspringapp.model

import com.example.kotlinspringapp.dto.BookDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long =0,

    @Column(unique = true)
    val title:String,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author:Author? = null,

    val publishYear:String,
    val bookCoverUrl:String,
) {
    constructor(bookDTO: BookDTO, author: Author?) :this(
        id = 0,
        title=bookDTO.title,
        author =author,
        publishYear=bookDTO.publishYear,
        bookCoverUrl=bookDTO.bookCoverUrl
    )
}
