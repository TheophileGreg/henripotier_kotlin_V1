package com.example.henripotier_kotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.view.View
import android.widget.Button
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    val cart = mutableSetOf<Book>()
    var book1 = Book("c8fabf68-8374-48fe-a7ea-a00ccd07afff", "Henri Potier à l'école des sorciers", 35, "http://henri-potier.xebia.fr/hp0.jpg");
    var book2 = Book("a460afed-e5e7-4e39-a39d-c885c05db861", "Henri Potier et la Chambre des secrets", 30, "http://henri-potier.xebia.fr/hp1.jpg");
    var book3 = Book("fcd1e6fa-a63f-4f75-9da4-b560020b6acc", "Henri Potier et le Prisonnier d'Azkaban", 30, "http://henri-potier.xebia.fr/hp2.jpg");
    var book4 = Book("c30968db-cb1d-442e-ad0f-80e37c077f89", "Henri Potier et la Coupe de feu", 29, "http://henri-potier.xebia.fr/hp3.jpg")

    val sliceDiscount = sliceDiscount(100.0, 12.0)
    val disounts = discounts(15.0, 5.0, sliceDiscount)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //println(parse(JsonReader(StringReader(jsonBooksList))));



        //Redondance set listener sur tout les boutons de la page et passer en parametre de la function pour ajouter le bon livre
        findViewById<Button>(R.id.book1_button).setOnClickListener{
            addBook1(it)

        }
        findViewById<Button>(R.id.book2_button).setOnClickListener{
            addBook2(it)

        }
        findViewById<Button>(R.id.book3_button).setOnClickListener{
            addBook3(it)

        }

        findViewById<Button>(R.id.getDiscount_button).setOnClickListener{

            //getDiscount()
            /*Fuel.get("http://henri-potier.xebia.fr/books/")
                .response { request, response, result ->
                    println(request)
                    println(response)
                    val (bytes, error) = result
                    if (bytes != null) {
                        println("[response bytes] ${String(bytes)}")
                    }
                }*/

            var bestDiscount = getBestDiscount(disounts, getSumCart());
            //Display le prix après réduction mais dès qu'une autre produit
        }
    }

    private fun getBestDiscount(discounts: discounts, cartPrice: Double):Double {
        var a = discounts.minusDiscount
        var b = (cartPrice / 100) * discounts.percentageDiscount
        var c = (cartPrice / discounts.sliceDiscount.sliceValue) * discounts.sliceDiscount.valueMinus
        return max(max(a, b), c)
    }


    /*fun parse(reader: JsonReader): List<Book> {
        val result = mutableListOf<Book>()

        reader.beginArray()
        while (reader.hasNext()) {
            var isbn: String = ""
            var title: String = ""
            var price: Int = 0
            var cover: String = ""


            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "isbn" -> isbn = reader.nextString()
                    "title" -> title = reader.nextString()
                    "price" -> price = reader.nextInt()
                    "cover" -> cover = reader.nextString()

                    else -> reader.skipValue()
                }
            }
            reader.endObject()

            val person = Book(isbn, title, price, cover);
            result.add(person)
        }
        reader.endArray()

        return result
    }*/

    //Comment récupérer le bon livre , if/When ?
    private fun addBook(view: View, bookAdded: Book) {
        cart.add(bookAdded)
        refreshSum(view)
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

        //Remettre a zero la réduction et réafficher le button "Obtenir une réduction"
    }

    private fun getSumCart(): Double{
        var sum = 0.0
        cart.forEach {
            sum += it.price;
        }
        return sum
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

