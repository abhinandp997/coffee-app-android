package com.example.coffeeapp.ui.screens.SearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.data.repository.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<CoffeeItem>>(emptyList())
    val searchResults: StateFlow<List<CoffeeItem>> = _searchResults.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            val items = coffeeRepository.getCoffeeItems()
            _searchResults.value = items.filter { item ->
                item.title.contains(query, ignoreCase = true)
            }
        }
    }

}