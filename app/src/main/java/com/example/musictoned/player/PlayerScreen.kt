package com.example.musictoned.player

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.musictoned.util.TimerUtils.formatTime
import com.example.musictoned.util.supportWideScreen
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextAlign

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onNavigateToRoutines: () -> Unit
) {
    // TODO - Provide actual workout time
    val time by viewModel.time.observeAsState(60000L.formatTime())

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
        Text(
            modifier = Modifier
                .offset(x = 103.dp, y = 110.dp),
            text = "EXERCISE 1 OUT OF 4", // TODO - Set this dynamically based on routine data
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(700),
                fontStyle = FontStyle.Italic,
                color = Color(0xBDFFFFFF),
                letterSpacing = 1.6.sp
        )
        Image(
            modifier = Modifier
                .offset(x = 48.dp, y = 130.dp)
                .width(300.dp)
                .height(300.dp),
            painter = painterResource(id = R.drawable.side_to_side_reaches), // TODO - Set dynamically
            contentDescription = "Workout picture",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .offset(x = 85.dp, y = 145.dp),
            text = "Side-to-side reaches", // TODO - Set dynamically
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xFFFFFFFF)
        )
        Text(
            modifier = Modifier
                .offset(x = 63.dp, y = 150.dp)
                .height(19.dp),
            text = " ♫ I’m so excited - The Pointer Sisters", // TODO - Set dynamically
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xBDFFFFFF)
        )
        Timer(
            modifier = Modifier
                .offset(x = 150.dp, y = 165.dp)
                .height(62.dp),
            time = time
        )
        Button(
            modifier = Modifier
                .offset(x = 155.dp, y = 150.dp),
            onClick = { viewModel.handleCountDownTimer() },
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = if (viewModel.isPlaying.value == true) "STOP" else "START",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600)
            )
        }
        Row {
            Text(
                modifier = Modifier
                    .offset(x = 20.dp, y = 150.dp)
                    .height(19.dp),
                text = "Up Next",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF)
            )
            Text(
                modifier = Modifier
                    .offset(x = 260.dp, y = 150.dp)
                    .clickable(
                        onClick = {}
                    )
                    .height(17.dp),
                text = "Skip >>",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xBDFFFFFF)
            )
        }
        Divider(
            modifier = Modifier
                .offset(x = 20.dp, y = 160.dp)
                .width(350.dp),
            color = Color(180, 167, 214),
            thickness = 0.5.dp
        )
    }
}

/**
 * Influenced by composable timer example
 * Ref: https://medium.com/geekculture/exploring-jetpack-compose-build-a-simple-countdown-timer-app-3151f8000529
 */

@Composable
fun Timer(
    modifier: Modifier,
    time: String
) {
    Text(
        modifier = modifier,
        text = time,
        fontSize = 36.sp,
        fontFamily = FontFamily(Font(R.font.lato_regular)),
        fontWeight = FontWeight(900),
        fontStyle = FontStyle.Italic,
        color = Color(0xFFFFFFFF)
    )
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