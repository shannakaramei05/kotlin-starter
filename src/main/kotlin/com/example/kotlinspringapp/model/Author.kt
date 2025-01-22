package com.example.kotlinspringapp.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val fullName:String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
    val books:MutableList<Book> = mutableListOf()

) {
    constructor(name: String) : this(0, name, mutableListOf())
}
