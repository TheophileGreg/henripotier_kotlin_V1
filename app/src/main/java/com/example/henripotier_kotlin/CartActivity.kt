package com.example.henripotier_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.henripotier_kotlin.R.layout.activity_cart
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_cart)
        var reducText = findViewById<TextView>(R.id.reduc_textView)
        var totalAfterText = findViewById<TextView>(R.id.totalAfterReduc_textView)
        var totalBeforeReductext = findViewById(R.id.totalPrice_textView) as TextView
        Cart.sumCart()
        totalBeforeReductext.text = "Total : ${Cart.getTotalCart()}€"

        val adapterRecycler = CartRecyclerViewAdapter(Cart.getCart().toMutableList(), {
            Cart.sumCart()
            totalBeforeReductext.text = "Total : ${Cart.getTotalCart()}€"
            reducText.text = null
            totalAfterText.text = null})

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterRecycler

       /* val adapter = CartAdapter(this, Cart.getCart().toMutableList(), {
            totalBeforeReductext.text = "Total : ${Cart.getTotalCart()}€"
            totalReducText.text = null
            totalAfterText.text = null})
        cart_listview.adapter = adapter*/





        val buttonReduc = findViewById<Button>(R.id.reducButton)
        buttonReduc.setOnClickListener{
            Cart.applyReduc()
            reducText.text = "Réduction : -${Cart.getReducAmount().toInt()}€"
            var total = Cart.getTotalCart() - Cart.getReducAmount().toInt()
            totalAfterText.text = "Total après réduction : ${total}€ "
        }

        val buttonRefresh = findViewById<Button>(R.id.button2)
        buttonRefresh.text = "Refresh"
        buttonRefresh.setOnClickListener{
            Cart.sumCart()
            reducText.text = null
            totalAfterText.text = null
            totalBeforeReductext.text = "Total : ${Cart.getTotalCart()}€"
        }


    }

    companion object {

        fun newIntent(context: Context): Intent {
            val detailIntent = Intent(context, CartActivity::class.java)
            return detailIntent
        }
    }


}