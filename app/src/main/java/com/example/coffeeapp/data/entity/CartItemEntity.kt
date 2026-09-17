package com.example.coffeeapp.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart_items",
    indices = [
        Index(
            value = ["coffeeId", "size"],
            unique = true
        )
    ]
)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val coffeeId: String,
    val title: String,
    val imageUrl: String,
    val price: Double,
    val size: String,
    val quantity: Int
)
