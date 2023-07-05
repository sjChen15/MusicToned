package com.example.musictoned.routines

import androidx.compose.runtime.Composable

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun RoutinesRoute(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToSpotifyBeta: () -> Unit,
    onNavigateToEditRoutine: (exerciseName: String) -> Unit,
    onNavigateToRoutines: (charOffset: Int) -> Unit,
    onNavigateToAnalytics: (charOffset: Int) -> Unit,
    onNavigateToSettings: (charOffset: Int) -> Unit

) {
    RoutinesScreen(
        onNavigateToRoutine = onNavigateToRoutine,
        onNavigateToEditRoutine = onNavigateToEditRoutine,
        onNavigateToRoutines = onNavigateToRoutines,
        onNavigateToAnalytics = onNavigateToAnalytics,
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToSpotifyBeta = onNavigateToSpotifyBeta
    )
}