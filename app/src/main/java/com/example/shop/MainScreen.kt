package com.example.shop

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.shop.presentation.viewmodel.ProductViewModel
import com.example.shop.ui.ProductScreen
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory
/*
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            BottomNavigation() {
                val items = listOf("Men's Clothing", "Women's Clothing", "Jewelry", "Electronics")
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { /* Provide an icon here */ },
                        label = { Text(text = item) },
                        selected = selectedItem == item,
                        onClick = {
                            selectedItem = item
                            when (item) {
                                "Men's Clothing" -> navController.navigate("mens_clothing")
                                "Women's Clothing" -> navController.navigate("womens_clothing")
                                "Jewelry" -> navController.navigate("jewelry")
                                "Electronics" -> navController.navigate("electronics")
                            }
                        }
                    )
                }
            }
        }
*/