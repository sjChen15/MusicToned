package com.example.musictoned.finishedWorkout

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.workoutcreation.AllWorkouts

@Composable
fun FinishedWorkoutScreen(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    routineID: Int
) {

    //var workout = AllWorkouts.getWorkout(routineID)

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
            painter = painterResource(id = R.drawable.celebrate),
            contentDescription = "Fireworks",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 10.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "GREAT JOB",
            style = TextStyle(
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(700),
                fontStyle = FontStyle.Italic,
                color = Color(0xFFFFFFFF),
                letterSpacing = 4.8.sp,
                textAlign = TextAlign.Center,
            )
        )

        Column(
            modifier = Modifier
                .padding( top = 25.dp, bottom = 25.dp ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "You completed your",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xBDFFFFFF),
                    letterSpacing = 2.4.sp,
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                //text = workout.name,
                text = "BASIC STRETCH",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 2.4.sp,
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "routine!",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xBDFFFFFF),
                    letterSpacing = 2.4.sp,
                    textAlign = TextAlign.Center,
                )
            )
        }


        Text(
            text = "Would you like to take a\nprogress picture?",
            modifier = Modifier
                .padding( bottom = 25.dp ),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )

        Button(
            modifier = Modifier
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 4.dp))
                .padding(start = 35.dp, end = 35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000),
            ),
            onClick = {
                      //TODO
            },
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "TAKE PICTURE",
                fontFamily = FontName,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
            )
        }

        ClickableText(
            text = AnnotatedString("Skip"),
            onClick = {
                onNavigateToRoutine(routineID)
            },
            modifier = Modifier
                .padding( top = 10.dp ),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                fontWeight = FontWeight(600),
                color = Color(0xBDFFFFFF),
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
            )
        )
    }
}

@Preview(name = "Player light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FinishedWorkoutScreenPreview() {
    MusicTonedTheme {
        FinishedWorkoutScreen(
            onNavigateToRoutine = {},
            routineID = 1,
        )
    }
}