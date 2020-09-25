package com.example.henripotier_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


    }

    companion object {

        fun newIntent(context: Context): Intent {
            val detailIntent = Intent(context, CartActivity::class.java)

            return detailIntent
        }
    }


}