package com.example.coffeeapp.data.local.repository

import android.util.Log
import com.example.coffeeapp.data.entity.CartItemEntity
import com.example.coffeeapp.data.local.CartDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {
    override fun getCartItems(): Flow<List<CartItemEntity>> {
        return cartDao.getCartItems()
    }

    override suspend fun addToCart(item: CartItemEntity) {
        val existingItem = cartDao.getCartItem(
            coffeeId = item.coffeeId,
            size = item.size
        )
        Log.d("CartItems","CartRepositoryImpl item ${item}")
        Log.d("CartItems","CartRepositoryImpl existingItem ${existingItem}")
        if (existingItem == null) {
            cartDao.insertCartItem(item)
        } else {
            cartDao.updateCartItem(
                existingItem.copy(
                    quantity = existingItem.quantity + item.quantity
                )
            )
        }
    }

    override suspend fun updateQuantity(item: CartItemEntity) {
        if (item.quantity<=0){
            cartDao.deleteCartItem(item)
        }else{
            cartDao.updateCartItem(item)
        }
    }

    override suspend fun removeFromCart(item: CartItemEntity) {
        cartDao.deleteCartItem(item)
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }
}