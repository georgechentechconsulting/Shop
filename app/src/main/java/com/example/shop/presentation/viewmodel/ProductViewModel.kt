package com.example.shop.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.model.Product
import com.example.shop.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    var products: List<Product> = emptyList()
        private set

    fun fetchProducts() {
        viewModelScope.launch {
            products = repository.getProducts()
        }
    }
}

