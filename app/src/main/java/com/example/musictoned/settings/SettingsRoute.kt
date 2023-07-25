package com.example.musictoned.settings

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun SettingsRoute(
    onNavigateToRoutines: (charOffset: Int) -> Unit,
    onNavigateToAnalytics: (charOffset: Int) -> Unit,
    onNavigateToSettings: (charOffset: Int) -> Unit
) {
    val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModelFactory())

    SettingsScreen(
        onNavigateToRoutines = onNavigateToRoutines,
        onNavigateToAnalytics = onNavigateToAnalytics,
        onNavigateToSettings = onNavigateToSettings,
        viewModel = settingsViewModel
    )
}