package com.example.coffeeapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.sharp.ListAlt
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    data object Home: BottomBarItem(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Cart: BottomBarItem(
        route = "cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    data object Explorer: BottomBarItem(
        route = "explorer",
        title = "Explorer",
        icon = Icons.Default.Explore
    )

    data object MyOrder: BottomBarItem(
        route = "my_order",
        title = "My Order",
        icon = Icons.Sharp.ListAlt
    )

    data object Profile: BottomBarItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}