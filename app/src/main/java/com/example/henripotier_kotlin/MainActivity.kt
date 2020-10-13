package com.example.henripotier_kotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONArray
import java.net.URL


class MainActivity : AppCompatActivity() {

    val urlBooks = "http://henri-potier.xebia.fr/books"
    val bookList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setListeners()
        runBlocking {
            getBooks()
            delay(5000)
        }

        val adapter = BooksAdapter(this, bookList)
        book_listview.adapter = adapter
        //Mise en place du floatingActionButton
        val context = this
        val floatingButtonCart =
            main_view.findViewById<FloatingActionButton>(R.id.floatingActionButtonCart)
        Picasso.get()
            .load("https://www.iconfinder.com/data/icons/shopping-cart-26/1000/Shopping-Basket-03-512.png")
            .into(floatingActionButtonCart);
        floatingButtonCart.setOnClickListener { view ->
            startActivity(CartActivity.newIntent(context))
            println("Open cart")

        }
    }


    fun parseResponse(response: String) {
        var isbn = ""
        var title = ""
        var price = 0
        var cover = ""
        var synopsis = ArrayList<String>()

        val jsonArray = JSONArray(response)

        (0 until jsonArray.length()).forEach { index ->
            val jsonObject = jsonArray.getJSONObject(index)
            isbn = jsonObject.getString("isbn")
            title = jsonObject.getString("title")
            price = jsonObject.getInt("price")
            cover = jsonObject.getString("cover")
            for (i in 0 until jsonObject.getJSONArray("synopsis").length()) {
                synopsis.add(jsonObject.getJSONArray("synopsis")[i].toString())
            }
            bookList.add(Book(isbn, title, price, cover, synopsis))
        }
    }

    fun getBooks() {
        Thread {
            val txt = URL(urlBooks).readText()
            Log.d("TestReq", txt)
            parseResponse(txt)

        }.start()
    }

    /*fun addBookView(view: View) {
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

    }*/


}



