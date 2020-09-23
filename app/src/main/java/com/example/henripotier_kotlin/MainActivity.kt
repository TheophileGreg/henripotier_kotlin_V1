package com.example.henripotier_kotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View

import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.URL

import kotlin.math.max


class MainActivity : AppCompatActivity() {

    val cart = cart();
    var book1 = Book(
        "c8fabf68-8374-48fe-a7ea-a00ccd07afff",
        "Henri Potier à l'école des sorciers",
        35,
        "http://henri-potier.xebia.fr/hp0.jpg"
    );
    var book2 = Book(
        "a460afed-e5e7-4e39-a39d-c885c05db861",
        "Henri Potier et la Chambre des secrets",
        30,
        "http://henri-potier.xebia.fr/hp1.jpg"
    );
    var book3 = Book(
        "fcd1e6fa-a63f-4f75-9da4-b560020b6acc",
        "Henri Potier et le Prisonnier d'Azkaban",
        30,
        "http://henri-potier.xebia.fr/hp2.jpg"
    );
    var book4 = Book(
        "c30968db-cb1d-442e-ad0f-80e37c077f89",
        "Henri Potier et la Coupe de feu",
        29,
        "http://henri-potier.xebia.fr/hp3.jpg"
    )
    var book5 = Book(
        "78ee5f25-b84f-45f7-bf33-6c7b30f1b502",
        "Henri Potier et l'Ordre du phénix",
        28,
        "http://henri-potier.xebia.fr/hp4.jpg"
    )

    val sliceDiscount = sliceDiscount(100.0, 12.0)
    val disounts = Discounts(15.0, 5.0, sliceDiscount)
    val urlBooks = "http://henri-potier.xebia.fr/books"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()

    }

    fun parseResponse(response: String) {
        val bookList = mutableListOf<Book>()
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

    fun addBookView(view: View) {
        when (view.id) {
            R.id.req_button -> Thread {

                val txt = URL(urlBooks).readText()
                Log.d("TestReq", txt)
                parseResponse(txt)

            }.start()
            R.id.book1_button -> cart.addBook(book1)
            R.id.book2_button -> cart.addBook(book2)
            R.id.book3_button -> cart.addBook(book3)
            R.id.book4_button -> cart.addBook(book4)
            R.id.book5_button -> cart.addBook(book5)
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



