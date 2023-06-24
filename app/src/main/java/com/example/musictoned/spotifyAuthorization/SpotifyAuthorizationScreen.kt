package com.example.musictoned.spotifyAuthorization

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musictoned.MainActivity
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.spotify.android.appremote.api.SpotifyAppRemote

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

private val clientId = "8b97731837b64c60b30ba2ecac7a8b74"
private val redirectUri = "http://com.example.musictoned/callback"
private var spotifyAppRemote: SpotifyAppRemote? = null

@Composable
fun SpotifyAuthorizationScreen(
    onNavigateToRoutines: () -> Unit
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Placeholder(
                onNavigateToRoutines = onNavigateToRoutines
            )
        }
    }
}

// TODO - Replace this placeholder with the components that you need
@Composable
private fun Placeholder(
    onNavigateToRoutines: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Text(
            text = "Placeholder for Spotify integration screen",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }

    // TODO - Needs to follow Spotify's design guidelines
    Button(
        // TODO - @harsh Need to add actual onClick handler
        //add onClick handles to authorize spotify
        onClick = { MainActivity().connectToSpotify() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 3.dp)
    ) {
        Text(
            text = "Authorize Spotify",
            style = MaterialTheme.typography.titleSmall
        )
    }
    Spacer(modifier = modifier)
    Button(
        onClick = onNavigateToRoutines,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 3.dp)
    ) {
        Text(
            text = "Navigate to routines",
            style = MaterialTheme.typography.titleSmall
        )
    }
}

fun onAuthorizeSpotify() {

//    val connectionParams = ConnectionParams.Builder(clientId)
//        .setRedirectUri(redirectUri)
//        .showAuthView(true)
//        .build()
//
//    SpotifyAppRemote.connect(ComponentActivity(), connectionParams, object : Connector.ConnectionListener {
//        override fun onConnected(appRemote: SpotifyAppRemote) {
//            spotifyAppRemote = appRemote
//            Log.d("MainActivity", "Connected! Yay!")
//            // Now you can start interacting with App Remote
//        }
//
//
//        override fun onFailure(throwable: Throwable) {
//            Log.e("MainActivity", throwable.message, throwable)
//            // Something went wrong when attempting to connect! Handle errors here
//        }
//    })

}

@Preview(name = "Spotify authorization light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Welcome authorization dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SpotifyAuthorizationScreenPreview() {
    MusicTonedTheme {
        SpotifyAuthorizationScreen(
            onNavigateToRoutines = {}
        )
    }
}