package com.example.coffeeapp.ui.screens.HomeScreen

import com.example.coffeeapp.data.model.Banner
import com.example.coffeeapp.data.model.Category
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.data.model.PopularItem

data class HomeUiState(
    val banners: List<Banner> = emptyList(),
    val categories: List<Category> = emptyList(),
    val popularItems: List<PopularItem> = emptyList(),
    val coffeeItems: List<CoffeeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategoryId: Int? = null,
    val selectedSort: SortOptions = SortOptions.DEFAULT,
    val searchQuery: String = "",
    val searchResults: List<CoffeeItem> = emptyList(),
    val visibleItems: List<CoffeeItem> = emptyList()
)
