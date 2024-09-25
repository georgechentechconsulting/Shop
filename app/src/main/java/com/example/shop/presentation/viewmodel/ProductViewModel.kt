package com.example.shop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.model.Product
import com.example.shop.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _products.value = repository.getProducts()
        }
    }

    fun filterProducts(query: String) {
        viewModelScope.launch {
            _products.value = repository.getProducts()
            _products.value = _products.value.filter { product ->
                product.title.contains(query, ignoreCase = true)
            }
        }
    }

    fun getProductById(productId: String?): Product? {
        return _products.value.firstOrNull { it.id.toString() == productId }
    }
}
