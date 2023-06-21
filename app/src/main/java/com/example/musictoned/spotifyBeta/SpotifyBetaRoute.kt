package com.example.musictoned.spotifyAuthorization

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun SpotifyBetaRoute(
    onNavigateToRoutines: () -> Unit
) {
    val spotifyBetaViewModel: SpotifyBetaViewModel = viewModel(factory = SpotifyBetaViewModelFactory())

    SpotifyBetaScreen(
        onNavigateToRoutines = onNavigateToRoutines
    )
}