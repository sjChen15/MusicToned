package com.example.musictoned

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.spotify.sdk.android.auth.AccountsQueryParameters.CLIENT_ID
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity

class SpotifyAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }
    }

    fun onStart() {

        val REDIRECT_URI = "com.example.musictoned://callback"
        val CLIENT_ID = "8b97731837b64c60b30ba2ecac7a8b74"

        val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
            CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            REDIRECT_URI
        )

        builder.setScopes(arrayOf("streaming"))
        val request: AuthorizationRequest = builder.build()

        //AuthorizationClient.openLoginInBrowser(this, request)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

    // Check if result comes from the correct activity
    if (requestCode == LoginActivity.REQUEST_CODE) {
        val response = AuthorizationClient.getResponse(resultCode, intent)
        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {}
            AuthorizationResponse.Type.ERROR -> {}
            else -> {}
        }
    }
    Log.d("MainActivity", "onActivityResult token: " + AuthorizationResponse.Type.TOKEN)
}

     fun onNewIntent(intent: Intent) {

        val uri = intent.data
        if (uri != null) {

            val response = AuthorizationResponse.fromUri(uri)

            if (response.type == AuthorizationResponse.Type.TOKEN) {
                // handle successful response
                Log.d("MainActivity", "onActivityResult token: " + AuthorizationResponse.Type.TOKEN)
            } else if (response.type == AuthorizationResponse.Type.ERROR) {
                // handle error response
                Log.d("MainActivity", "onActivityResult token: " + AuthorizationResponse.Type.ERROR)
            }

        }
    }