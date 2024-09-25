package com.example.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.shop.data.repository.ProductRepository
import com.example.shop.presentation.viewmodel.ProductViewModel
import com.example.shop.presentation.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {

    private val productRepository by lazy { ProductRepository() }
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(productRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Your composable content here
            productViewModel.fetchProducts()
        }
    }
}