package com.example.coffeeapp.data.local.repository

import com.example.coffeeapp.data.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<List<CartItemEntity>>

    suspend fun addToCart(item: CartItemEntity)

    suspend fun updateQuantity(item: CartItemEntity)

    suspend fun removeFromCart(item: CartItemEntity)

    suspend fun clearCart()

}