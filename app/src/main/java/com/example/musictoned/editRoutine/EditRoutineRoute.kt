package com.example.musictoned.editRoutine

import androidx.compose.runtime.Composable

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
    EditRoutineScreen(
        onNavigateToRoutine = onNavigateToRoutine,
        onNavigateToAddExercise = onNavigateToAddExercise,
        exerciseName = exerciseName
    )
}