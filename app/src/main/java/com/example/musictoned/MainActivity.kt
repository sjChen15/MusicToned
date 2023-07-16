package com.example.musictoned

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.LocalStorage
import com.example.musictoned.workoutcreation.ExerciseTempos
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity.REQUEST_CODE


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

        //val intent = Intent(this@MainActivity, SpotifyAuth::class.java)
        //this@MainActivity.startActivity(intent)

    }

    override fun onStop() {
        super.onStop()
    }

    public fun connectToSpotify() {

    }
}