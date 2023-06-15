package com.example.musictoned

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.musictoned.ui.theme.MusicTonedTheme

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/MainActivity.kt
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Initialize the navigation host
         */
        setContent {
            MusicTonedTheme {
                MusicTonedNavHost()
            }
        }
    }
}