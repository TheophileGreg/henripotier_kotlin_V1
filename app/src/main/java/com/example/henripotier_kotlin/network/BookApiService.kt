package com.example.henripotier_kotlin.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://henri-potier.xebia.fr/books"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BookApiService{
    @GET("realstate")
    fun getProperties():
            Call<String>

}

object BookApi {
    val retrofitService : BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}