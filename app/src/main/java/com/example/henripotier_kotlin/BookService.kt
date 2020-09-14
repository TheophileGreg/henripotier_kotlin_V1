package com.example.henripotier_kotlin


import retrofit2.Call
import retrofit2.http.GET

interface BookService{
    @GET("/books")
    fun getCurrentBooksData(): Call<BookResponse>
}