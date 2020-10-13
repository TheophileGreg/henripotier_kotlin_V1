package com.example.henripotier_kotlin

object Cart {

    init {
        println("Panier créé")
    }

    private val bookInCart = mutableListOf<Book>()
    private var totalCart : Double = 0.0
    private var reducApply : Boolean = false

    fun getTotalCart(): Double{
        return totalCart
    }

    fun getReducApply(): Boolean{
        return reducApply
    }

    fun addBook(book:Book): Boolean{
        var validation : Boolean = bookInCart.add(book)
        sumCart()
        reducApply = false
        return validation
    }

    fun removeBook(book: Book): Boolean{
        sumCart()
        reducApply = false
        return bookInCart.remove(book)
    }

    fun sumCart() : Boolean{
        var res : Double = 0.0
        for (book in bookInCart) {
            val bookPrice : Int = book.price ?: return false //Pas sur de ça
            res += bookPrice
        }
        this.totalCart = res
        return true
    }

    fun getCart(): Array<Book>{
        return bookInCart.toTypedArray()
    }


}