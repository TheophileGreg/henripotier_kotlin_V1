package com.example.henripotier_kotlin

import org.json.JSONArray


data class Book(
    var isbn: String? = null,
    var title: String? = null,
    var price: Int? = 0,
    var cover: String? = null
)
