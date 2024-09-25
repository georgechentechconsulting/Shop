package com.example.shop.data.repository


import com.example.shop.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import com.google.gson.Gson

class ProductRepository {

    private val client = OkHttpClient()
    private val gson = Gson()

    suspend fun getProducts(): List<Product> {
        val request = Request.Builder()
            .url("https://fakestoreapi.com/products")
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    parseProductJson(response)
                } else {
                    emptyList()
                }
            }
        }
    }

    private fun parseProductJson(response: Response): List<Product> {
        return gson.fromJson(response.body?.string(), Array<Product>::class.java).toList()
    }
}
