package com.example.coffeeapp.data.repository

import com.example.coffeeapp.data.model.Banner
import com.example.coffeeapp.data.model.Category
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.data.model.PopularItem
import com.example.coffeeapp.data.remote.FirebaseDatasource
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val firebaseDatasource: FirebaseDatasource
): CoffeeRepository {
    override suspend fun getBanners(): List<Banner> {
        return firebaseDatasource.getBanner()
    }

    override suspend fun getCategories(): List<Category> {
        return firebaseDatasource.getCategory()
    }

    override suspend fun getPopularItems(): List<PopularItem> {
        return firebaseDatasource.getPopularItems()
    }

    override suspend fun getCoffeeItems(): List<CoffeeItem> {
        return firebaseDatasource.getItems()
    }
}