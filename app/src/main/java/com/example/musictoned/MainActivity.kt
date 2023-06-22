package com.example.musictoned

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote

//import spotify from the src/libs/spotify-app-remote-release-0.7.2.aar

private val clientId = "8b97731837b64c60b30ba2ecac7a8b74"
private val redirectUri = "com.example.musictoned://callback"
private var spotifyAppRemote: SpotifyAppRemote? = null

/*
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
    override fun onStart() {
        super.onStart()
        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectUri)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d("MainActivity", "Connected! Yay!")
                // Now you can start interacting with App Remote
                connected()
            }


            override fun onFailure(throwable: Throwable) {
                Log.e("MainActivity", throwable.message, throwable)
                // Something went wrong when attempting to connect! Handle errors here
            }
        })
    }

    private fun connected() {

    }

    override fun onStop() {
        super.onStop()
    }
}