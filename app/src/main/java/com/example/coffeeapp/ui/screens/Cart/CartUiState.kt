package com.example.coffeeapp.ui.screens.Cart

import com.example.coffeeapp.data.entity.CartItemEntity

data class CartUiState(
    val items: List<CartItemEntity> = emptyList(),
    val totalPrice: Double = 0.0,
    val isEmpty: Boolean = true
)
