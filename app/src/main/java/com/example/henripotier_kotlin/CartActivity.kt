package com.example.henripotier_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

    }

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_PRICE = "price"

        fun newIntent(context: Context, book: Book): Intent {
            val detailIntent = Intent(context, CartActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, book.title)
            detailIntent.putExtra(EXTRA_PRICE, book.price)

            return detailIntent
        }
    }
}