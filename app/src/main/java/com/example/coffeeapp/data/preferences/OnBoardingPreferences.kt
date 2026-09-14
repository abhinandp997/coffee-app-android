package com.example.coffeeapp.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "coffe_app_preferences")

class OnBoardingPreferences(
    private val context: Context
) {

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    val hasCompletedOnBoarding: Flow<Boolean> =
        context.dataStore.data.map { preferences -> preferences[ONBOARDING_COMPLETED] ?: false }

    suspend fun completeOnBoarding() {
        context.dataStore.edit { preferences -> preferences[ONBOARDING_COMPLETED] = true }
    }

}