package com.example.musictoned.spotify

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class SpotifyConnect {

    companion object {
        private val accessToken = "BQA2_B9Z7hjqQkDmAz8hFHkLxF8R75HQqD0qCSrCw9suzjNycvc00DZV-M_cGK1roLszNM1qa01q_wcGOIS-IX2TMt0R27MoOHCUdsuimis2Z_1__oBD1hLLzNWYTLaKBE-9Ohqf4eatVElorT8dAg0v_Y2LhrhkxUvS15tzBqSBBkKNuWtcslWtem7Aj2jgvfllPZWXcQ"
        private val clientId = "8b97731837b64c60b30ba2ecac7a8b74"
        private val redirectUri = "com.example.musictoned://callback"
        private var spotifyAppRemote: SpotifyAppRemote? = null

        fun connect(context: Context) {
            val connectionParams = ConnectionParams.Builder(clientId)
                .setRedirectUri(redirectUri)
                .showAuthView(true)
                .build()

            SpotifyAppRemote.connect(context, connectionParams, object : Connector.ConnectionListener {
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

            val policy = ThreadPolicy.Builder().permitAll().build()

            StrictMode.setThreadPolicy(policy)

            val client = OkHttpClient()

            val sUrl = "https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V"

            var result: String? = null
            try {
                // Create URL
                val url = URL(sUrl)
                // Build request
                val request = Request.Builder().url(url).addHeader("Authorization", "Bearer BQBQu72_UgpuFHbeFLLARSj0_kMc2zCQjoGFSy4FAewH1GqjqVfphb8VNwC7KRldmVNUy5V3gJ9C9s2BMm7pqcPYYf4Q2cND-Y_gul4pi-bxtfeSMtd8SWxbPZVsaF7X4KWKwPleeiZKg9WwYEE8jHc4uYX_AUaDXNVGLwW0XlwjpuLHuYXhx9MIaaQ1OVDJEnzNeBLgBA").build()
                // Execute request
                val response = client.newCall(request).execute()
                result = response.body?.string()
            }
            catch(err:Error) {
                print("Error when executing get request: "+err.localizedMessage)
            }

            Log.d("SpotifyConnect", result.toString())



//             test connection
//            spotifyAppRemote?.let {
//                playPlaylist()
//                it.playerApi.play(playlistURI)
//                // Subscribe to PlayerState
//                it.playerApi.subscribeToPlayerState().setEventCallback {
//                    val track: Track = it.track
//                    Log.d("SpotifyConnect", track.name + " by " + track.artist.name)
//                }
            }

        private fun playPlaylist() {
            spotifyAppRemote?.let {
                val playlistURI = "spotify:playlist:6hwjHl90iQXO8JdBAbA3ky"
                it.playerApi.play(playlistURI)
            }
        }

        fun disconnect() {
            spotifyAppRemote?.let {
                SpotifyAppRemote.disconnect(it)
            }
        }
    }
}