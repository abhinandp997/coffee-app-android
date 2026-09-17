package com.example.coffeeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeapp.data.entity.CartItemEntity

@Database(
    entities = [CartItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}