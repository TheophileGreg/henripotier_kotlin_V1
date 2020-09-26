package com.example.henripotier_kotlin

object Cart {

    init {
        println("Panier créé")
    }

    private val bookInCart = mutableListOf<Book>()
    private var totalCart : Double = 0.0
    private var totalCartDiscount : Double = 0.0

    fun addBook(book:Book): Boolean{
        var validation : Boolean = bookInCart.add(book)
        sumCart()
        println("ajoutez au panier ${book.title}")
        return validation
    }

    fun getSum() : Double{
        return totalCart
    }

    fun sumCart() : Boolean{
        var res : Double = 0.0
        for (book in bookInCart) {
            val bookPrice : Int = book.price ?: return false //Pas sur de ça
            res += bookPrice
        }
        totalCart = res
        return true
    }

    fun getCart(): Array<Book>{
        return bookInCart.toTypedArray()
    }


}