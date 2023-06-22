package com.example.musictoned.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun WelcomeScreen(
    onNavigateToSpotifyAuthorization: () -> Unit
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
        ) {
            Placeholder(
                onNavigateToSpotifyAuthorization = onNavigateToSpotifyAuthorization
            )
        }
    }
}

// TODO - Replace this placeholder with the components that you need
@Composable
private fun Placeholder(
    onNavigateToSpotifyAuthorization: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome to MusicToned",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onNavigateToSpotifyAuthorization,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Get Started")
        }
    }
}

@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Welcome dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RoutinesScreenPreview() {
    MusicTonedTheme {
        WelcomeScreen(
            onNavigateToSpotifyAuthorization = {}
        )
    }
}