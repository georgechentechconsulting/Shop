package com.example.shop.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.shop.data.model.Product
import androidx.navigation.NavController
import com.example.shop.presentation.viewmodel.ProductViewModel

@Composable
fun ProductScreen(viewModel: ProductViewModel, category: String, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val products by viewModel.products.collectAsState()

    val filteredProducts = products.filter { product ->
        product.category == category && product.title.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.filterProducts(searchQuery)
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Use Box to limit the height of the LazyVerticalGrid
        Box(modifier = Modifier
            .weight(1f)
            .height(250.dp) // Set the desired height limit for the grid
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Change the number of columns as needed
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp) // Add some padding around the grid
            ) {
                if (filteredProducts.isEmpty()) {
                    item {
                        Text("No products found", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
                    }
                }

                items(filteredProducts) { product ->
                    ProductItem(product, navController)
                }
            }
        }
    }
}


@Composable
fun ProductItem(product: Product, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("ProductDetail/${product.id}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Price: $${product.price}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
