package com.example.musictoned.routine

import androidx.compose.runtime.Composable

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun RoutineRoute(
    onNavigateToEditRoutine: (routineId: Int) -> Unit,
    onNavigateToRoutines: () -> Unit,
    onNavigateToPlayer: (routineId: Int) -> Unit,
    routineID: Int?
) {
    RoutineScreen(
        onNavigateToEditRoutine = onNavigateToEditRoutine,
        onNavigateToRoutines = onNavigateToRoutines,
        onNavigateToPlayer = onNavigateToPlayer,
        routineID = routineID
    )
}