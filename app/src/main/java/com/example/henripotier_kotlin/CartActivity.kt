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
import org.w3c.dom.Text

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_cart)

        val adapter = CartAdapter(this, Cart.getCart().toMutableList())
        cart_listview.adapter = adapter

        var totalBeforeReductext = findViewById(R.id.totalPrice_textView) as TextView

        var totalReducText = findViewById<TextView>(R.id.reduc_textView)
        var totalAfterText = findViewById<TextView>(R.id.totalAfterReduc_textView)

        val buttonReduc = findViewById<Button>(R.id.reducButton)
        buttonReduc.setOnClickListener{
            Cart.applyReduc()
            totalReducText.text = "Réduction : -${Cart.getReducAmount()}€"
            var total = Cart.getTotalCart() - Cart.getReducAmount()
            totalAfterText.text = "Total après réduction : ${total}€ "
        }

        val buttonRefresh = findViewById<Button>(R.id.button2)
        buttonRefresh.text = "Refresh"
        buttonRefresh.setOnClickListener{
            adapter.notifyDataSetChanged()
            totalReducText.text = null
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