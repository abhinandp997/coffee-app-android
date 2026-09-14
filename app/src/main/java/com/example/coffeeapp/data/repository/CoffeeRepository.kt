package com.example.coffeeapp.data.repository

import com.example.coffeeapp.data.model.Banner
import com.example.coffeeapp.data.model.Category
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.data.model.PopularItem

interface CoffeeRepository {
    suspend fun getBanners(): List<Banner>
    suspend fun getCategories(): List<Category>
    suspend fun getPopularItems(): List<PopularItem>
    suspend fun getCoffeeItems(): List<CoffeeItem>
}