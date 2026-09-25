package com.example.coffeeapp.ui.screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.ui.screens.HomeScreen.components.Carousel
import com.example.coffeeapp.ui.screens.Components.CategoryMenu
import com.example.coffeeapp.ui.screens.HomeScreen.components.CategoryRow
import com.example.coffeeapp.ui.screens.HomeScreen.components.HomeScreenTopBar

@Composable
fun HomeScreen(
    onSearch: (String) -> Unit,
    onCoffeeItemClick: (CoffeeItem) -> Unit
) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            HomeScreenTopBar(
                query = uiState.searchQuery,
                onQueryChange = { homeViewModel.onSearchQueryChanged(it) },
                onSearchClick = {
                    if (uiState.searchQuery.isNotBlank()) {
                        onSearch(uiState.searchQuery)
                    }
                },
                onSuggestionsClick = { item ->
                    onCoffeeItemClick(item)
                },
                suggestions = uiState.searchResults,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    )
    { innerPadding ->

        if (uiState.isLoading) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }

        } else if (uiState.error != null) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = uiState.error ?: "Something went wrong")
            }

        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
//                    .verticalScroll(rememberScrollState())
            ) {
                Carousel(items = uiState.popularItems)

                Spacer(modifier = Modifier.height(20.dp))

                CategoryRow(
                    categories = uiState.categories,
                    selectedCategoryId = uiState.selectedCategoryId,
                    uiState = uiState,
                    homeViewModel = homeViewModel,
                    onCategoryClick = { category -> homeViewModel.onCategorySelected(category) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                val filteredItems = if (uiState.selectedCategoryId == null) {
                    uiState.coffeeItems
                } else {
                    uiState.coffeeItems.filter { it.categoryId == uiState.selectedCategoryId.toString() }
                }

                val sortedItems = when (uiState.selectedSort) {
                    SortOptions.DEFAULT -> filteredItems
                    SortOptions.PRICE_LOW_TO_HIGH -> filteredItems.sortedBy { it.price }
                    SortOptions.PRICE_HIGH_TO_LOW -> filteredItems.sortedByDescending { it.price }
                    SortOptions.RATING_HIGH_TO_LOW -> filteredItems.sortedByDescending { it.rating }
                    SortOptions.NAME_A_TO_Z -> filteredItems.sortedBy { it.title }
                }

                CategoryMenu(
                    items = sortedItems,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}