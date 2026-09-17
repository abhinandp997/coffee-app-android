package com.example.coffeeapp.ui.screens.Cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.entity.CartItemEntity
import com.example.coffeeapp.data.local.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        observeCart()
    }

    private fun observeCart() {
        viewModelScope.launch {
            cartRepository.getCartItems().collect { items ->
                val total = items.sumOf { it.price * it.quantity }
                _uiState.value = CartUiState(
                    totalPrice = total,
                    items = items,
                    isEmpty = items.isEmpty()
                )
            }
        }
    }

    fun addToCart(item: CartItemEntity) {
        viewModelScope.launch {
            cartRepository.addToCart(item)
        }
    }

    fun increaseQuantity(item: CartItemEntity) {
        viewModelScope.launch {
            cartRepository.updateQuantity(
                item = item.copy(quantity = item.quantity + 1)
            )
        }
    }

    fun decreaseQuantity(item: CartItemEntity) {
        viewModelScope.launch {
            val newQty = item.quantity - 1

            if (newQty <= 0){
                cartRepository.removeFromCart(item)
            }else{
                cartRepository.updateQuantity(
                    item = item.copy(quantity = newQty)
                )
            }
        }
    }

    fun removeItem(item: CartItemEntity) {
        viewModelScope.launch {
            cartRepository.removeFromCart(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }

}