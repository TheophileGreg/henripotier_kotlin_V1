package com.example.henripotier_kotlin

import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.view.View
import android.widget.Button
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi

import kotlinx.android.synthetic.main.activity_main.*

import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.max
import kotlin.math.min



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
    val disounts = discounts(15.0, 5.0, sliceDiscount)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()


       fun setListeners() {
            val clickableView: List<View> =
                listOf(book1_button, book2_button, book4_button, book3_button, book5_button)
            for (item in clickableView) {
                item.setOnClickListener { addBook(it) }
            }


           fun addBook(view: View) {
               when (view.id) {
                   R.id.book1_button -> cart.add(book1)
                   R.id.book2_button -> cart.add(book2)
                   R.id.book3_button -> cart.add(book3)
                   R.id.book4_button -> cart.add(book4)
                   R.id.book5_button -> cart.add(book5)
               }
               refreshSum(view)
               refreshCartDetails(view)

           }


            fun getBestDiscount(discounts: discounts, cartPrice: Double): Double {
                var a = discounts.minusDiscount
                var b = (cartPrice / 100) * discounts.percentageDiscount
                var c =
                    (cartPrice / discounts.sliceDiscount.sliceValue) * discounts.sliceDiscount.valueMinus
                val discountText = findViewById<TextView>(R.id.discountPrice_text);
                val priceAfterDiscountText = findViewById<TextView>(R.id.priceAfterDiscount_text);
                discountText.text = max(max(a, b), c).toString()
                priceAfterDiscountText.text =
                    "Le prix après réduction est maintenant de : " + (cartPrice - max(
                        max(a, b),
                        c
                    )).toString()
                return max(max(a, b), c)


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
    }
}

