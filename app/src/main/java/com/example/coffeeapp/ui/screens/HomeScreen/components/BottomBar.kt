package com.example.coffeeapp.ui.screens.HomeScreen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coffeeapp.navigation.BottomBarItem

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomBarItem.Home,
        BottomBarItem.Cart,
        BottomBarItem.Explorer,
        BottomBarItem.MyOrder,
        BottomBarItem.Profile,
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
//        contentColor = Color.White,
        modifier = modifier
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = modifier.size(25.dp)
                    )
                },
                label = {
                    Text(item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    unselectedIconColor = MaterialTheme.colorScheme.surfaceVariant,
                    selectedIconColor = MaterialTheme.colorScheme.background,
                    selectedTextColor = MaterialTheme.colorScheme.background,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}