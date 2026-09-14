package com.example.coffeeapp.di

import android.content.Context
import com.example.coffeeapp.data.preferences.OnBoardingPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOnBoardingPreferences(
        @ApplicationContext context: Context
    ): OnBoardingPreferences {
        return OnBoardingPreferences(context)
    }

}