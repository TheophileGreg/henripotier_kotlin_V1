package com.example.henripotier_kotlin

object Cart {

    init {
        println("Panier créé")
    }

    private val bookInCart = mutableListOf<Book>()

    fun addBook(book:Book): Boolean{
        return bookInCart.add(book)
    }

    fun getCart(): Array<Book>{
        return bookInCart.toTypedArray()
    }


}