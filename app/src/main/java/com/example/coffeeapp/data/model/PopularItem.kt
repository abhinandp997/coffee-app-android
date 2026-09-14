package com.example.coffeeapp.data.model

data class PopularItem(
    val description: String = "",
    val extra: String = "",
    val picUrl: List<String> = emptyList(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val title: String = ""
)
