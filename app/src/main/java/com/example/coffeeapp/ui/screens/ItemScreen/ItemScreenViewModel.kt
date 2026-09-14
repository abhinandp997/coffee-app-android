package com.example.coffeeapp.ui.screens.ItemScreen

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
class ItemScreenViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) : ViewModel() {
    private val _itemResult = MutableStateFlow<CoffeeItem?>(null)
    val itemResult: StateFlow<CoffeeItem?> = _itemResult.asStateFlow()

    fun getItem(itemTitle: String) {
        viewModelScope.launch {
            val result = coffeeRepository.getCoffeeItems()
            _itemResult.value = result.find { item -> item.title == itemTitle }
        }
    }
}