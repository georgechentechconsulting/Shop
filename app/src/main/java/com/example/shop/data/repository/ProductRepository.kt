package com.example.shop.data.repository


import com.example.shop.data.model.Product
import com.example.shop.data.network.RetrofitInstance


class ProductRepository {
    suspend fun getProducts(): List<Product> {
        return RetrofitInstance.api.getProducts()
    }
}
