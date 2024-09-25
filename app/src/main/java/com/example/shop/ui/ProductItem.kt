package com.example.shop.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.shop.data.model.Product

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberImagePainter(product.image),
            contentDescription = null,
            modifier = Modifier
                .height(128.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.title)
        Text(text = "$${product.price}")
    }
}