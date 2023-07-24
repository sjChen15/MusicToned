package com.example.musictoned.player

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.musictoned.spotify.SpotifyConnect
import com.example.musictoned.workoutcreation.Exercise
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise
import java.util.LinkedList

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel,
    onNavigateToRoutines: () -> Unit,
    onNavigateToFinishedWorkoutRoutine: (routineID: Int?) -> Unit
) {
    // TODO - Provide actual workout time
    val time by viewModel.time.observeAsState(viewModel.playerScreenData.exerciseTimeMillis.formatTime())

    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White, Color(red = 116, green = 0, blue = 184)
                    ),
                    startY = 200f,
                    endY = 210f
                )
            )
            .fillMaxSize()
            .offset(y = -(15).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.routines_waves),
            contentDescription = "Waves",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Image(
            modifier = Modifier
                .offset(x = 170.dp, y = -(5).dp)
                .clickable(
                    onClick = {
                        SpotifyConnect.pauseSong()
                        onNavigateToRoutines()
                    }
                )
                .width(22.dp)
                .height(22.dp),
            painter = painterResource(id = R.drawable.x),
            contentDescription = "Exit workout player",
        )
        Text(
            text = viewModel.playerScreenData.routineName.uppercase(),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(700),
            fontStyle = FontStyle.Italic,
            color = Color(0xFFFFFFFF),
            letterSpacing = 2.4.sp
        )
        Text(
            text = "Exercise ${viewModel.playerScreenData.exerciseIndex} out of ${viewModel.playerScreenData.exerciseCount}".uppercase(),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(700),
            fontStyle = FontStyle.Italic,
            color = Color(0xBDFFFFFF),
            letterSpacing = 1.6.sp
        )
        Image(
            modifier = Modifier
                .offset(y = 25.dp)
                .width(300.dp)
                .height(300.dp),
            painter = painterResource(id = viewModel.playerScreenData.exerciseImageId),
            contentDescription = "Workout picture",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .offset(y = 35.dp),
            text = viewModel.playerScreenData.exerciseName,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xFFFFFFFF)
        )
        Text(
            modifier = Modifier
                .offset(y = 43.dp)
                .height(19.dp),
            text = "♫ ${viewModel.playerScreenData.songName}",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xBDFFFFFF)
        )
        Timer(
            modifier = Modifier
                .offset(y = 70.dp)
                .height(62.dp),
            time = time
        )

        var buttonText by remember { mutableStateOf("START") }
        Button(
            modifier = Modifier
                .offset(y = 55.dp),
            onClick = {
                viewModel.handleCountDownTimer()
                buttonText = if (viewModel.isPlaying.value == true) "STOP" else "START"
                      },
        ) {
            Text(
                text = buttonText,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600)
            )
        }
        Row {
            Text(
                modifier = Modifier
                    .offset(x = -(100).dp, y = 60.dp)
                    .height(19.dp),
                text = "Up Next",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF)
            )

            var skipText by remember { mutableStateOf("Skip >>") }
            skipText = if(viewModel.playerScreenData.exerciseIndex == viewModel.playerScreenData.exerciseCount){
                "Finish"
            } else {
                "Skip >>"
            }
            Text(
                modifier = Modifier
                    .offset(x = 100.dp, y = 60.dp)
                    .clickable(
                        onClick = {
                            if(viewModel.playerScreenData.exerciseIndex == viewModel.playerScreenData.exerciseCount){
                                SpotifyConnect.pauseSong()
                                onNavigateToFinishedWorkoutRoutine(viewModel.playerScreenData.routineID)
                            } else {
                                viewModel.onSkipPressed()
                            }
                            buttonText = if (viewModel.isPlaying.value == true) "STOP" else "START"

                        }
                    )
                    .height(17.dp),
                text = skipText,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xBDFFFFFF)
            )
        }
        Divider(
            modifier = Modifier
                .offset(x = 0.dp, y = 70.dp)
                .width(310.dp),
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

@Preview(name = "Player light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PlayerScreenPreview() {
    MusicTonedTheme {
        PlayerScreen(
            viewModel = PlayerViewModel(
                0,
                Workout(
                "Basic Stretch",
                LinkedList<WorkoutExercise>(listOf(
                    WorkoutExercise(
                        Exercise(
                            "Side-to-side reaches",
                            listOf("triceps"),
                            500,
                            "side_to_side_reaches"
                        ),
                        56,
                        song = "I'm so excited - The Pointer Sisters"
                    ),
                    WorkoutExercise(
                        Exercise(
                            "Side-to-side reaches",
                            listOf("triceps"),
                            500,
                            "side_to_side_reaches"
                        ),
                        56,
                        song = "I'm so excited - The Pointer Sisters"
                    ),
                    WorkoutExercise(
                        Exercise(
                            "Side-to-side reaches",
                            listOf("triceps"),
                            500,
                            "side_to_side_reaches"
                        ),
                        56,
                        song = "I'm so excited - The Pointer Sisters"
                    ),
                    WorkoutExercise(
                        Exercise(
                            "Side-to-side reaches",
                            listOf("triceps"),
                            500,
                            "side_to_side_reaches"
                        ),
                        56,
                        song = "I'm so excited - The Pointer Sisters"
                    )
                ))
            )
            ),
            onNavigateToRoutines = {},
            onNavigateToFinishedWorkoutRoutine = {}
        )
    }
}