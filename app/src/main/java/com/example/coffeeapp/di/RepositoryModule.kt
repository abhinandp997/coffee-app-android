package com.example.coffeeapp.di

import com.example.coffeeapp.data.repository.CoffeeRepository
import com.example.coffeeapp.data.repository.CoffeeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoffeeRepository(implementation: CoffeeRepositoryImpl): CoffeeRepository

}