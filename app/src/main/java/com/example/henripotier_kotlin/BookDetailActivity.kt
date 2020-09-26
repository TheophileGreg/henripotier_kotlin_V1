package com.example.henripotier_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BookDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_BOOK = "BookDetailActivity:book"
        fun newIntent(context: Context, book: Book): Intent {
            val detailIntent = Intent(context, BookDetailActivity::class.java)

            detailIntent.putExtra(EXTRA_BOOK, book)

            return detailIntent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        val book : Book = intent.getParcelableExtra<Book>(EXTRA_BOOK) ?: return


        val titleText = findViewById<TextView>(R.id.titleBook_text)
        val pricetext = findViewById(R.id.price_textView) as TextView

        titleText.text = " Vous voulez obtenir des détails sur le livre : ${book.cover} "
        pricetext.text = book.price.toString()

    }
}