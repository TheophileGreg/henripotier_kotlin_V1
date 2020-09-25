package com.example.henripotier_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val adapter = BooksAdapter(this, Cart.getCart().toMutableList())
        cart_listview.adapter = adapter

    }

    companion object {

        fun newIntent(context: Context): Intent {
            val detailIntent = Intent(context, CartActivity::class.java)

            return detailIntent
        }
    }


}