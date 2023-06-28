package com.example.musictoned.routine

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun RoutineRoute(
    onNavigateToEditRoutine: (exerciseName: String) -> Unit,
    onNavigateToRoutines: () -> Unit,
    routineID: Int?
) {
    val routineViewModel: RoutineViewModel = viewModel(factory = RoutineViewModelFactory())

    RoutineScreen(
        onNavigateToEditRoutine = onNavigateToEditRoutine,
        onNavigateToRoutines = onNavigateToRoutines,
        routineID = routineID
    )
}