package com.example.musictoned.editRoutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeViewModel.kt
 */

class EditRoutineViewModel() : ViewModel() {
}

class EditRoutineViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRoutineViewModel::class.java)) {
            return EditRoutineViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
