package com.example.musictoned.yourGoals

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun YourGoalsRoute(
    onClickFinish: () -> Unit
) {
    val yourGoalsViewModel: YourGoalsViewModel = viewModel(factory = YourGoalsViewModelFactory())

    YourGoalsScreen(
        onClickFinish = onClickFinish,
        viewModel = yourGoalsViewModel
    )
}