package com.example.shop.presentation.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart

@Composable
fun NavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomNavigation {
        val categories = listOf("Mens", "Womens", "Jewelery", "Electronics")

        categories.forEach { category ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                },
                label = { Text(category) },
                selected = currentRoute == category,
                onClick = {
                    navController.navigate(category) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
    }
}
