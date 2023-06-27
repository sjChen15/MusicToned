package com.example.musictoned.editRoutine

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musictoned.addExercise.AddExerciseViewModelFactory
import com.example.musictoned.routine.RoutineViewModel
import com.example.musictoned.routine.RoutineViewModelFactory
import com.example.musictoned.workoutcreation.Workout

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun EditRoutineRoute(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    onNavigateToAddExercise: () -> Unit,
    exerciseName: String?
) {
    val editRoutineViewModel: EditRoutineViewModel = viewModel(factory = EditRoutineViewModelFactory())

    EditRoutineScreen(
        onNavigateToRoutine = onNavigateToRoutine,
        onNavigateToAddExercise = onNavigateToAddExercise,
        exerciseName = exerciseName
    )
}