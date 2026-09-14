package com.example.coffeeapp.navigation

import android.net.Uri


sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object HomeScreen : Routes("home_screen")
    object OnBoarding : Routes("onboarding")
    object Cart : Routes("cart")
    object Explorer : Routes("explorer")
    object MyOrder : Routes("my_order")
    object Profile : Routes("profile")
    object SearchResult : Routes("search_query/{query}") {
        fun createRoute(query: String): String {
            return "search_query/${Uri.encode(query)}"
        }
    }
    object ItemScreen : Routes("item_screen/{itemTitle}"){
        fun createRoute(itemTitle: String): String {
            return "item_screen/${Uri.encode(itemTitle)}"
        }
    }
}