package com.example.musictoned.player

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.musictoned.ui.theme.MusicTonedTheme

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun PlayerScreen(
) {
}

@Preview(name = "Player light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PlayerScreenPreview() {
    MusicTonedTheme {
        PlayerScreen(
        )
    }
}