package com.example.kotlinspringapp.model

import com.example.kotlinspringapp.dto.BookResponseDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long =0,

    @Column(unique = true)
    val title:String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    val author:Author? = null,

    val publishYear:String,
    val bookCoverUrl:String,

    @OneToMany(mappedBy = "book", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val vote: MutableList<Vote> = mutableListOf(),

    val totalVotes:Int = 0,
    val averageRating:Double = 0.0
) {
    constructor(bookResponseDTO: BookResponseDTO, author: Author?) :this(
        id = 0,
        title=bookResponseDTO.title,
        author =author,
        publishYear=bookResponseDTO.publishYear,
        bookCoverUrl=bookResponseDTO.bookCoverUrl
    )
}
