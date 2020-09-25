package com.example.henripotier_kotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

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
        Log.d("TestOnCreate", "test")

        //setListeners()
        runBlocking {
            getBooks()

            delay(5000)
        }

        val adapter = BooksAdapter(this, bookList)
        book_listview.adapter = adapter
        val context = this
        book_listview.setOnItemClickListener { _, _, position, _ ->
            // 1
            val bookDetail = bookList[position]


            // 2
            val detailIntent = BookDetailActivity.newIntent(context, bookDetail)

            // 3
            startActivity(detailIntent)
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
        //println(bookList.toString())
    }


    /*fun setListeners() {
        val clickableView: List<View> =
            listOf(book1_button, book2_button, book4_button, book3_button, book5_button, req_button)
        for (item in clickableView) {
            item.setOnClickListener { addBookView(it) }
        }
    }*/

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



