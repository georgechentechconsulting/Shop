package com.example.shop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shop.data.repository.ProductRepository
import com.example.shop.presentation.viewmodel.ProductViewModel
import com.example.shop.presentation.viewmodel.ProductViewModelFactory
import com.example.shop.ui.ProductScreen
import com.example.shop.ui.ProductDetailScreen
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shop.presentation.view.NavBar

class MainActivity : ComponentActivity() {
    private lateinit var productViewModel: ProductViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewModel
        val productRepository = ProductRepository()
        val factory = ProductViewModelFactory(productRepository)
        productViewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)

        setContent {
            val navController = rememberNavController()
            Scaffold(bottomBar = {
                if (navController.currentBackStackEntryAsState().value?.destination?.route != "ProductDetail/{productId}") {
                    NavBar(navController)
                }
            }) { paddingValues ->
                NavHost(navController, startDestination = "Mens", Modifier.padding(paddingValues)) {
                    composable("Mens") {
                        ProductScreen(viewModel = productViewModel, category = "men's clothing", navController)
                    }
                    composable("Womens") {
                        ProductScreen(viewModel = productViewModel, category = "women's clothing", navController)
                    }
                    composable("Jewelery") {
                        ProductScreen(viewModel = productViewModel, category = "jewelery", navController)
                    }
                    composable("Electronics") {
                        ProductScreen(viewModel = productViewModel, category = "electronics", navController)
                    }
                    composable("ProductDetail/{productId}") { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        val product = productViewModel.getProductById(productId)

                        if (product != null) {
                            ProductDetailScreen(product = product, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
