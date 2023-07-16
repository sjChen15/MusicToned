package com.example.musictoned.spotify
import android.content.Context
import android.util.Log
import com.example.musictoned.MainActivity
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track

class SpotifyConnect {

    companion object {

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
            spotifyAppRemote?.let {
                // Play a playlist
                val playlistURI = "spotify:playlist:6hwjHl90iQXO8JdBAbA3ky"
                it.playerApi.play(playlistURI)
                // Subscribe to PlayerState
                it.playerApi.subscribeToPlayerState().setEventCallback {
                    val track: Track = it.track
                    Log.d("SpotifyConnect", track.name + " by " + track.artist.name)
                }
            }
        }
    }
}