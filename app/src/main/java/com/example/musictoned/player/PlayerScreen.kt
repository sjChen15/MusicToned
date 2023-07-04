package com.example.musictoned.player

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun PlayerScreen(
) {
    PlayerScreenBackground()
}

@Composable
fun PlayerScreenBackground() {
    Surface(modifier = Modifier
        .supportWideScreen()
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White, Color(red = 116, green = 0, blue = 184)
                        ),
                        startY = 200f,
                        endY = 210f
                    ))
        ) {
            Image(
                painter = painterResource(id = R.drawable.routines_waves),
                contentDescription = "Waves",
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 0.dp, y = -(10).dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview(name = "Player light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PlayerScreenPreview() {
    MusicTonedTheme {
        PlayerScreen(
        )
    }
}