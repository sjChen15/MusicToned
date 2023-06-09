package com.example.musictoned.editRoutine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musictoned.workoutcreation.AllWorkouts

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
