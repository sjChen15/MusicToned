package com.example.musictoned.welcome

import androidx.compose.runtime.Composable

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun WelcomeRoute(
    onClickCreateAnAccount: () -> Unit
) {
    WelcomeScreen(
        onClickCreateAnAccount = onClickCreateAnAccount
    )
}