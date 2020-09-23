package com.example.henripotier_kotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View

import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.net.URL

import kotlin.math.max


class MainActivity : AppCompatActivity() {

    val cart = cart();


    val sliceDiscount = sliceDiscount(100.0, 12.0)
    val disounts = Discounts(15.0, 5.0, sliceDiscount)
    val urlBooks = "http://henri-potier.xebia.fr/books"
    val bookList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
            getBooks()
        }
        setListeners()
    }

    fun parseResponse(response: String) {
        var isbn = ""
        var title = ""
        var price = 0
        var cover = ""

        val jsonArray = JSONArray(response)

        (0..6).forEach { index ->
            val jsonObject = jsonArray.getJSONObject(index)
            isbn = jsonObject.getString("isbn")
            title = jsonObject.getString("title")
            price = jsonObject.getInt("price")
            cover = jsonObject.getString("cover")
            bookList.add(Book(isbn, title, price, cover))
        }
        //println(bookList.toString())
    }


    fun setListeners() {
        val clickableView: List<View> =
            listOf(book1_button, book2_button, book4_button, book3_button, book5_button, req_button)
        for (item in clickableView) {
            item.setOnClickListener { addBookView(it) }
        }
    }

    fun getBooks() {
        Thread {

            val txt = URL(urlBooks).readText()
            Log.d("TestReq", txt)
            parseResponse(txt)

        }.start()
    }

    fun addBookView(view: View) {
        when (view.id) {
            R.id.book1_button -> cart.addBook(bookList[1])
            R.id.book2_button -> cart.addBook(bookList[2])
            R.id.book3_button -> cart.addBook(bookList[3])
            R.id.book4_button -> cart.addBook(bookList[4])
            R.id.book5_button -> cart.addBook(bookList[5])
        }
        refreshSum(view)
        refreshCartDetails(view)

    }


    fun refreshSum(view: View) {
        val sumText = findViewById<TextView>(R.id.sum_text);
        var sum = cart.getSumCart()
        sumText.text = " Le total du panier avant remise est de : " + sum.toString() + "€ "

        //Remettre a zero la réduction et réafficher le button "Obtenir une réduction"
    }


    fun refreshCartDetails(view: View) {
        val cartText = findViewById<TextView>(R.id.cartDetails_text);
        var cartDetails = "Votre panier contient : "
        cart.getListOfBook().forEach {
            cartDetails += it.title + "; ";
        }
        cartText.text = cartDetails.toString()

    }


}



