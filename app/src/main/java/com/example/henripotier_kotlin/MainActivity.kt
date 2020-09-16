package com.example.henripotier_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    val cart = mutableSetOf<Book>()
    var book1 = Book("book1Henri", "J.K", 25.0);
    var book2 = Book("book2Henri", "J.K", 35.0);
    var book3 = Book("book3Henri", "J.K", 30.0);
    var book4 = Book("book4Henri", "J.K", 31.0);
    var book5 = Book("book5Henri", "J.K", 33.0);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()


    }

    private fun setListeners(){
        val clickableView: List<View> = listOf(book1_button, book2_button, book4_button, book3_button, book5_button)
        for (item in clickableView){
            item.setOnClickListener{addBook(it)}
        }
    }

    fun addBook(view: View){
        when(view.id){
            R.id.book1_button -> cart.add(book1)
            R.id.book2_button -> cart.add(book2)
            R.id.book3_button -> cart.add(book3)
            R.id.book4_button -> cart.add(book4)
            R.id.book5_button -> cart.add(book5)
        }
        refreshSum(view)
        refreshCartDetails(view)
    }

    private fun addBook1(view: View) {
        cart.add(book1)
        refreshSum(view)
        refreshCartDetails(view)
    }

    private fun addBook2(view: View) {
        cart.add(book2)
        refreshSum(view)
        refreshCartDetails(view)
    }
    private fun addBook3(view: View) {
        cart.add(book3)
        refreshSum(view)
        refreshCartDetails(view)
    }

    private fun refreshSum(view: View){
        val sumText = findViewById<TextView>(R.id.sum_text);
        var sum = 0.0
        cart.forEach {
            sum += it.price;
        }
        sumText.text = " Le total du panier avant remise est de : " + sum.toString() + "€ "

    }

    private fun refreshCartDetails(view: View){
        val cartText = findViewById<TextView>(R.id.cartDetails_text);
        var cartDetails = "Votre panier contient : "
        cart.forEach {
            cartDetails += it.title + "; " ;
        }
        cartText.text = cartDetails.toString()

    }
}