package com.example.musictoned.yourGoals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class YourGoalsViewModel : ViewModel() {
    var isGainMuscleChecked by mutableStateOf(false)
        private set

    fun updateGainMuscleChecked(input: Boolean) {
        isGainMuscleChecked = input
    }

    var isImproveEnduranceChecked by mutableStateOf(false)
        private set

    fun updateImproveEnduranceChecked(input: Boolean) {
        isImproveEnduranceChecked = input
    }

    var isLoseWeightChecked by mutableStateOf(false)
        private set

    fun updateLoseWeightChecked(input: Boolean) {
        isLoseWeightChecked = input
    }

    var isIncreaseFlexibilityChecked by mutableStateOf(false)
        private set

    fun updateIncreaseFlexibilityChecked(input: Boolean) {
        isIncreaseFlexibilityChecked = input
    }

    var isExerciseRegularlyChecked by mutableStateOf(false)
        private set

    fun updateExerciseRegularlyChecked(input: Boolean) {
        isExerciseRegularlyChecked = input
    }
}

class YourGoalsViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(YourGoalsViewModel::class.java)) {
            return YourGoalsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}