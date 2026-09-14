package com.example.coffeeapp.ui.screens.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.preferences.OnBoardingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onBoardingPreferences: OnBoardingPreferences
): ViewModel() {

    val hasCompletedOnboarding = onBoardingPreferences.hasCompletedOnBoarding
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun completeOnboarding(){
        viewModelScope.launch {
            onBoardingPreferences.completeOnBoarding()
        }
    }

}