package com.example.shop.data.network

import com.example.shop.data.model.Product
import retrofit2.Call
import retrofit2.http.GET


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}



object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}