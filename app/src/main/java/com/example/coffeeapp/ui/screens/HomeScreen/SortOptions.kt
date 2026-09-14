package com.example.coffeeapp.ui.screens.HomeScreen

enum class SortOptions(
    val title: String
) {
    DEFAULT("Sort By"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    RATING_HIGH_TO_LOW("Rating: High to Low"),
    NAME_A_TO_Z("Name: A-Z")
}