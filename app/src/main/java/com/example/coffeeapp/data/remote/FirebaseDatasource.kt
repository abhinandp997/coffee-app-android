package com.example.coffeeapp.data.remote

import com.example.coffeeapp.data.model.Banner
import com.example.coffeeapp.data.model.Category
import com.example.coffeeapp.data.model.CoffeeItem
import com.example.coffeeapp.data.model.PopularItem
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDatasource @Inject constructor(
    private val database: DatabaseReference
) {
    suspend fun getBanner(): List<Banner> {
        val snapshot = database.child("Banner")
            .get()
            .await()

        return snapshot.children.mapNotNull { it.getValue(Banner::class.java) }
    }

    suspend fun getCategory(): List<Category>{
        val snapshot = database.child("Category")
            .get()
            .await()

        return snapshot.children.mapNotNull { it.getValue(Category::class.java) }
    }

    suspend fun getItems(): List<CoffeeItem>{
        val snapshot = database.child("Items")
            .get()
            .await()

        return snapshot.children.mapNotNull { it.getValue(CoffeeItem::class.java) }
    }

    suspend fun getPopularItems(): List<PopularItem>{
        val snapshot = database.child("Popular")
            .get()
            .await()

        return snapshot.children.mapNotNull { it.getValue(PopularItem::class.java) }
    }
}