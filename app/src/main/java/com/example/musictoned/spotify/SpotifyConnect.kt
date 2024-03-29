package com.example.musictoned.spotify

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL


class SpotifyConnect {

    companion object {
        private val accessToken = "BQC-EniDqfD05bbZFmgMmiaslfHVohpQph0GQx8RE_2cbb5hbIIUe6GPkvl3CS_6ecqPrxl_dIM9EuixMqyiEv67n0RQaHqXPuLgBdSsrvPTNW0nZWp_5jCdlSuTM2jNeLRHG908C5TI8pkXcdRrFMHPADsYskhQCYWZRhBmtFmX9xQCKZP-0NM5onv7FXzaBw8PeKrKleGtBiOtfuFUnTMOeUuERZzXar2cUUjuf_BEI7rVPXt0SeKB1jkLtNRkuJcxlg4tL-zj4kspYU63dtk4"
        private val clientId = "8b97731837b64c60b30ba2ecac7a8b74"
        private val redirectUri = "com.example.musictoned://callback"
        private var spotifyAppRemote: SpotifyAppRemote? = null
        val playlistURI = "spotify:playlist:6hwjHl90iQXO8JdBAbA3ky"
        private val songInfo = mutableMapOf<String, List<Double>>()


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

        }

        private fun getPlaylistInfo() {
            //overriding thread policy to allow network access on main thread
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val client = OkHttpClient()
            val sUrl = "https://api.spotify.com/v1/playlists/6hwjHl90iQXO8JdBAbA3ky/tracks"

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
            val totalCount = json?.get("total")

            //make comma separated string of song ids located in items array, assisted by Github Copilot
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

            Log.d("SpotifyConnect", "total count is $totalCount")
            Log.d("SpotifyConnect", songIds)

            getSongBPM(songIds)
        }

        //get song bpm in array from song ids
        private fun getSongBPM(songIds: String){
            //overriding thread policy to allow network access on main thread
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val client = OkHttpClient()
            val sUrl = "https://api.spotify.com/v1/audio-features?ids="
            val sUrlWithIds = sUrl + songIds.replace(",", "%2C")

            var result: String? = null
            try {
                // Create URL
                val url = URL(sUrlWithIds)
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

            //get song id, tempo, and energy from result json and make key value pairs as id: [tempo, energy]
            val items = json?.getJSONArray("audio_features")

            //song info is a songID with associated tempo and energy rating
            for (i in 0 until items!!.length()) {
                val item = items.getJSONObject(i)
                val id = item.getString("id")
                val tempo = item.getDouble("tempo")
                val energy = item.getDouble("energy")
                songInfo[id] = listOf(tempo, energy)
            }

            Log.d("SpotifyConnect", "Song Info${songInfo.toString()}")

            //get song id with highest energy in a tempo range
            val songId = getSongId(listOf(140.0, 150.0), songInfo)

            //play song
            playSong("spotify:track:$songId")
            getSongName(songId)

            pauseSongAfterTime(1000)
        }

        fun getSongInfo(): MutableMap<String, List<Double>>{
            return songInfo
        }
        //get song id with highest energy in a tempo range
        fun getSongId(tempoRange: List<Double>, songInfo: MutableMap<String, List<Double>>): String {

            //get song ids in tempo range
            val songIds = mutableListOf<String>()
            for (song in songInfo) {
                if (song.value[0] > tempoRange[0] && song.value[0] < tempoRange[1] ) {
                    songIds.add(song.key)
                }
            }

            //return a random song id from the list of song ids
            return songIds.random()
        }

        fun getSongName(id: String): String{
            //overriding thread policy to allow network access on main thread
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val client = OkHttpClient()
            val sUrl = "https://api.spotify.com/v1/tracks/$id"

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

            //get track name (name) and artist name (artists->name) from result json
            val name = json?.getString("name")
            val artists = json?.getJSONArray("artists")
            val artist = artists?.getJSONObject(0)?.getString("name")

            Log.d("SpotifyConnect", "$name by $artist")

            return "$name by $artist"
        }

        private fun playPlaylist() {
            spotifyAppRemote?.let {
                it.playerApi.play(playlistURI)
            }
        }

        fun playSong(songURI: String) {
                    spotifyAppRemote?.let {
                        it.playerApi.play(songURI)
                    }
                }

        //timer for song
        private fun pauseSongAfterTime(time: Long) {
            Handler(Looper.getMainLooper()).postDelayed({
                pauseSong()
            }, time)
        }

        fun pauseSong() {
                    spotifyAppRemote?.let {
                        it.playerApi.pause()
                    }
                }

        fun resumeSong(){
            spotifyAppRemote?.let { it.playerApi.resume() }
        }

        fun disconnect() {
            spotifyAppRemote?.let {
                SpotifyAppRemote.disconnect(it)
            }
        }
    }
}