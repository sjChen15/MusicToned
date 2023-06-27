package com.example.musictoned.routines

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun RoutinesRoute(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToSpotifyBeta: () -> Unit,
    onNavigateToEditRoutine: () -> Unit
) {
    val routinesViewModel: RoutinesViewModel = viewModel(factory = RoutinesViewModelFactory())

    RoutinesScreen(
        onNavigateToRoutine = onNavigateToRoutine,
        onNavigateToSpotifyBeta = onNavigateToSpotifyBeta,
        onNavigateToEditRoutine = onNavigateToEditRoutine
    )
}