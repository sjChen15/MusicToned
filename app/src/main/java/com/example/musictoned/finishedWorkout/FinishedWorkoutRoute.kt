package com.example.musictoned.finishedWorkout

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun FinishedWorkoutRoute(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    routineID: Int
) {
    val FinishedWorkoutViewModel: FinishedWorkoutViewModel = viewModel(factory = FinishedWorkoutViewModelFactory())

    FinishedWorkoutScreen(
        onNavigateToRoutine = onNavigateToRoutine,
        routineID = routineID
    )
}