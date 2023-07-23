package com.example.musictoned.finishedWorkout

import androidx.compose.runtime.Composable

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun FinishedWorkoutRoute(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    routineID: Int
) {
    FinishedWorkoutScreen(
        onNavigateToRoutine = onNavigateToRoutine,
        routineID = routineID
    )
}