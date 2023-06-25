package com.example.musictoned.aboutYou

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun AboutYouRoute(
    onClickContinue: () -> Unit
) {
    val aboutYouViewModel: AboutYouViewModel = viewModel(factory = AboutYouViewModelFactory())

    AboutYouScreen(
        onClickContinue = onClickContinue,
        viewModel = aboutYouViewModel
    )
}