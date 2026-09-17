package com.example.coffeeapp.di

import android.content.Context
import androidx.room.Room
import com.example.coffeeapp.data.local.CartDao
import com.example.coffeeapp.data.local.CoffeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CoffeeDatabase {
        return Room.databaseBuilder(
            context,
            CoffeeDatabase::class.java,
            "coffee_database"
        ).build()
    }

    @Provides
    fun provideDao(database: CoffeeDatabase): CartDao {
        return database.cartDao()
    }

}