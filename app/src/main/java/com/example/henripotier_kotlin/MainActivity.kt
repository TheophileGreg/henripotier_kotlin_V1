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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
    }

    private fun addBook1(view: View) {
        cart.add(book1)
        refreshSum(view)
    }
    private fun addBook2(view: View) {
        cart.add(book2)
        refreshSum(view)
    }
    private fun addBook3(view: View) {
        cart.add(book3)
        refreshSum(view)
    }

    private fun refreshSum(view: View){
        val sumText = findViewById<TextView>(R.id.sum_text);
        var sum = 0.0
        cart.forEach {
            sum += it.price;
        }
        sumText.text = sum.toString()

    }
}