package com.example.henripotier_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.example.henripotier_kotlin.R.layout.activity_cart
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_cart.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_cart)

        val adapter = CartAdapter(this, Cart.getCart().toMutableList())
        cart_listview.adapter = adapter


        val buttonRefresh = findViewById<Button>(R.id.button2)
        buttonRefresh.setOnClickListener{
            adapter.notifyDataSetChanged()
        }

        val pricetext = findViewById(R.id.totalPrice_textView) as TextView
        pricetext.text = "Total : ${Cart.getSum().toString()}"


    }

    companion object {

        fun newIntent(context: Context): Intent {
            val detailIntent = Intent(context, CartActivity::class.java)
            return detailIntent
        }
    }


}