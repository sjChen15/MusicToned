package com.example.musictoned.aboutYou

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.musictoned.profile.HeightUnit
import com.example.musictoned.profile.WeightUnit

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeViewModel.kt
 */

class AboutYouViewModel : ViewModel() {
    var name by mutableStateOf("")
        private set

    fun updateName(input: String) {
        name = input
    }

    var age by mutableStateOf("")
        private set

    fun updateAge(input: String) {
        age = input
    }

    var weight by mutableStateOf("")
        private set

    fun updateWeight(input: String) {
        weight = input
    }

    var height by mutableStateOf("")
        private set

    fun updateHeight(input: String) {
        height = input
    }

    val weightUnits = listOf(WeightUnit.KG, WeightUnit.LB)

    var isWeightUnitMenuExpanded by mutableStateOf(false)
        private set

    fun updateWeightUnitMenuExpanded(input: Boolean) {
        isWeightUnitMenuExpanded = input
    }

    var weightUnit by mutableStateOf(WeightUnit.KG)
        private set

    fun updateWeightUnit(input: WeightUnit) {
        weightUnit = input
    }

    val heightUnits = listOf(HeightUnit.M, HeightUnit.FT)

    var isHeightUnitMenuExpanded by mutableStateOf(false)
        private set

    fun updateHeightUnitMenuExpanded(input: Boolean) {
        isHeightUnitMenuExpanded = input
    }

    var heightUnit by mutableStateOf(HeightUnit.M)
        private set

    fun updateHeightUnit(input: HeightUnit) {
        heightUnit = input
    }
}

class AboutYouViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutYouViewModel::class.java)) {
            return AboutYouViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}