package com.example.musictoned.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musictoned.profile.HeightUnit
import com.example.musictoned.profile.WeightUnit

class SettingsViewModel : ViewModel() {
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

    var calorieGoal by mutableStateOf("")
        private set

    fun updateCalorieGoal(input: String) {
        calorieGoal = input
    }
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

class SettingsViewModelFactory : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)){
            return SettingsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}