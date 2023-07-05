package com.example.musictoned.player

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun PlayerScreen(
    onNavigateToRoutines: () -> Unit
) {
    PlayerScreenBackground()
    Column {
        Image(
            modifier = Modifier
                .offset(x = 350.dp, y = 95.dp)
                .clickable(
                    onClick = onNavigateToRoutines
                )
                .width(18.dp)
                .height(18.dp),
            painter = painterResource(id = R.drawable.x),
            contentDescription = "Exit workout player",
        )
        Text(
            modifier = Modifier
                .offset(x = 95.dp, y = 105.dp),
            text = "BASIC STRETCH", // TODO - Set this based on the routine name
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(700),
            fontStyle = FontStyle.Italic,
            color = Color(0xFFFFFFFF),
            letterSpacing = 2.4.sp
        )
    }
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
            onNavigateToRoutines = {}
        )
    }
}