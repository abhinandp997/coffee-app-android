package com.example.coffeeapp.ui.screens.HomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.model.Category
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.data.repository.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val banners = coffeeRepository.getBanners()
                val categories = coffeeRepository.getCategories()
                val popularItems = coffeeRepository.getPopularItems()
                val items = coffeeRepository.getCoffeeItems()
                Log.d("Items", "banner ${banners}")
                _uiState.value = HomeUiState(
                    banners = banners,
                    categories = categories,
                    popularItems = popularItems,
                    coffeeItems = items,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onCategorySelected(category: Category) {
        _uiState.update { currentState ->
            if (currentState.selectedCategoryId == category.id) {
                currentState.copy(
                    selectedCategoryId = null
                )
            } else {
                currentState.copy(
                    selectedCategoryId = category.id
                )
            }

        }
    }

    fun onSortSelected(sortOptions: SortOptions) {
        _uiState.update {
            it.copy(selectedSort = sortOptions)
        }
    }

    fun onSearchQueryChanged(searchQuery: String) {

        val results = if (searchQuery.isBlank()) {
            emptyList()
        } else {
            _uiState.value.coffeeItems.filter { item ->
                item.title.contains(searchQuery.trim(), ignoreCase = true)
            }
        }
        Log.d("Search","HomeViewModel searchQuery ${searchQuery}")
        Log.d("Search","HomeViewModel results ${results}")
        _uiState.update {
            it.copy(
                searchQuery = searchQuery.trim(),
                searchResults = results
            )
        }
    }

    fun getSearchResults(): List<CoffeeItem> {
        val query = uiState.value.searchQuery

        if (query.isEmpty()) return emptyList()

        return uiState.value.coffeeItems.filter { item ->
            item.title.contains(query, ignoreCase = true) ||
                    item.description.contains(query, ignoreCase = true)
        }
    }

}