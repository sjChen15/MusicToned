package com.example.musictoned

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.musictoned.spotify.SpotifyConnect
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.LocalStorage
import com.example.musictoned.workoutcreation.ExerciseTempos


/*
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/MainActivity.kt
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Pass context to classes
         */
        LocalStorage.setApplication(this)
        ExerciseTempos.setApplication(this)
        /**
         * Initialize the navigation host
         */
        setContent {
            MusicTonedTheme {
                MusicTonedNavHost()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        //SpotifyConnect.connect(this)

        //the following line is the one that is causing the error, but it returns access token
        //SpotifyAuth.connect(this)
    }

    override fun onStop() {
        super.onStop()
        //SpotifyConnect.disconnect()
    }
}