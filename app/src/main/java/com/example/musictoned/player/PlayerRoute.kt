package com.example.musictoned.player

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun PlayerRoute(
    onNavigateToRoutines: () -> Unit,
    routineID: Int
) {
    val playerViewModel: PlayerViewModel = viewModel(factory = PlayerViewModelFactory(routineID))

    PlayerScreen(
        viewModel = playerViewModel,
        onNavigateToRoutines = onNavigateToRoutines
    )
}