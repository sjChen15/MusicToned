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
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class SpotifyConnect {

    companion object {
        private val accessToken = "BQDDeM-CzF2V3hm-EfhfC7dUsoGp7TDViefcWO5GCBMIsIScRHMIFEUJ8nQn946XuKjw5fBOG45XiEdPPSdPkKEWkkhVKIaAgeo5qZZ-3Dduv25W1YMihdSTtYPl7BbKTx_K1xSBfFzJsAFFIS07hZOnTCJYDI8Vvwc4G0VxOqni3tfXxVdgs568TzHvuc874XVZ1Mb2uQ"
        private val clientId = "8b97731837b64c60b30ba2ecac7a8b74"
        private val redirectUri = "com.example.musictoned://callback"
        private var spotifyAppRemote: SpotifyAppRemote? = null
        val playlistURI = "spotify:playlist:6hwjHl90iQXO8JdBAbA3ky"

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

            getPlaylistInfo()

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

        private fun getPlaylistInfo() {
            //overriding thread policy to allow network access on main thread
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val client = OkHttpClient()
            val sUrl = "https://api.spotify.com/v1/playlists/0bOMiotwvb5HHQG490U015/tracks"

            var result: String? = null
            try {
                // Create URL
                val url = URL(sUrl)
                // Build request
                val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $accessToken").build()
                // Execute request
                val response = client.newCall(request).execute()
                result = response.body?.string()
            }
            catch(err:Error) {
                print("Error when executing get request: "+err.localizedMessage)
            }

            //convert result to json
            val json = result?.let { JSONObject(it) }

            //get total info from result json
            val total_count = json?.get("total")

            //make comma separated string of song ids located in items array
            val items = json?.getJSONArray("items")
            var songIds = ""
            for (i in 0 until items!!.length()) {
                val item = items.getJSONObject(i)
                val track = item.getJSONObject("track")
                val id = track.getString("id")
                songIds += id
                if (i < items.length() - 1) {
                    songIds += ","
                }
            }

            Log.d("SpotifyConnect", songIds.toString())
        }

        private fun playPlaylist() {
            spotifyAppRemote?.let {
                it.playerApi.play(playlistURI)
            }
        }

        private fun playSong(songURI: String) {
                    spotifyAppRemote?.let {
                        it.playerApi.play(songURI)
                    }
                }

        private fun pauseSong() {
                    spotifyAppRemote?.let {
                        it.playerApi.pause()
                    }
                }



        fun disconnect() {
            spotifyAppRemote?.let {
                SpotifyAppRemote.disconnect(it)
            }
        }
    }
}