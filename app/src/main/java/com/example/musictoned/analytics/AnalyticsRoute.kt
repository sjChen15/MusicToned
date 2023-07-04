package com.example.musictoned.analytics

import androidx.compose.runtime.Composable

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun AnalyticsRoute(
    onNavigateToRoutines: (charOffset: Int) -> Unit,
    onNavigateToAnalytics: (charOffset: Int) -> Unit,
    onNavigateToSettings: (charOffset: Int) -> Unit
) {
    AnalyticsScreen(
        onNavigateToRoutines = onNavigateToRoutines,
        onNavigateToAnalytics = onNavigateToAnalytics,
        onNavigateToSettings = onNavigateToSettings
    )
}