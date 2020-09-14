package com.example.henripotier_kotlin

class cart(){

    private val listBook: MutableSet<Book> = mutableSetOf<Book>();

    fun addBook(book: Book){
        listBook.add(book)
    }

    fun getListOfBook(): MutableSet<Book>{
        return listBook;
    }

    fun removeBook(book: Book){
        listBook.remove(book)
    }

    fun resetCart(){
        listBook.clear()
    }
    fun getSumCart(): Double{
        var sum = 0.0
        listBook.forEach {
            sum += it.price!!;
        }
        return sum
    }
}


