package com.example.musictoned.routine

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun RoutineScreen(
    onNavigateToEditRoutine: (routineId: Int) -> Unit,
    onNavigateToRoutines: () -> Unit,
    onNavigateToPlayer: (routineId: Int) -> Unit,
    routineID: Int?
) {
    val workout: Workout = if(routineID == null){
        Workout("New Workout")
    }
    else{
        AllWorkouts.getWorkout(routineID)
    }

    Log.d("ROUTINE ID:", routineID.toString())


    Surface(modifier = Modifier
        .supportWideScreen()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
                .navigationBarsPadding(),
            backgroundColor = Color(0x00000000),
            topBar = {
                //Text(text = routineID.toString(), modifier = Modifier.padding(start = 100.dp))
                TopBar(
                    onNavigateToEditRoutine = onNavigateToEditRoutine,
                    onNavigateToRoutines = onNavigateToRoutines,
                    workout = workout
                )
            },
            bottomBar = {
                BottomBar( modifier = Modifier.padding( top = 5.dp ),
                    start = {
                        if (routineID != null) {
                            onNavigateToPlayer(routineID)
                        }
                    }
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier.padding(innerPadding)){
                    Workouts(
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                        workout = workout
                    )
                }
            }
        )
    }
}

@Composable
private fun TopBar(
    onNavigateToEditRoutine: (routineId: Int) -> Unit,
    onNavigateToRoutines: () -> Unit,
    modifier: Modifier = Modifier,
    workout: Workout,
) {
    Column(
        modifier = modifier.padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 10.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row{
                Text(
                    modifier = modifier
                        .clickable {
                            onNavigateToRoutines()
                        },
                    text = "< ",
                    color = Color(0xFF5E60CE),
                    fontSize = 30.sp,
                    letterSpacing = 2.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.W700,
                )
                Text(
                    text = workout.name,
                    color = Color(0xFF5E60CE),
                    fontSize = 30.sp,
                    letterSpacing = 2.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontName,
                    fontWeight = FontWeight.W700,
                )
            }
            Image(
                modifier = modifier
                    .size(25.dp)
                    .clickable {
                        onNavigateToEditRoutine(workout.hashCode())
                    },
                painter = painterResource(id = R.drawable.edit_button),
                contentDescription = "Edit Button",
            )
        }
        Divider(color = Color(0x69B4A7D6), thickness = 2.dp)
    }
}

@Composable
private fun Workouts(
    modifier: Modifier = Modifier,
    workout: Workout
) {

    var timeElapsed = 0L

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ){
        workout.exercises.forEach {exercise ->
            Exercise( exercise = exercise, startTime = timeElapsed )
            timeElapsed += exercise.getLength()
        }
    }
}

@Composable
private fun Exercise(
    modifier: Modifier = Modifier,
    exercise: WorkoutExercise,
    startTime: Long,
){
    val startMinutes = startTime / 60
    val startSeconds = startTime % 60

    val endTime = startTime + exercise.getLength()
    val endMinutes = endTime / 60
    val endSeconds = endTime % 60

    var startText = ""
    var endText = ""

    if( startTime < 599 ){
        startText = String.format("%02d:%02d", startMinutes, startSeconds)
    } else {
        startText = String.format("%d:%02d", startMinutes, startSeconds)
    }

    if( endTime < 599 ){
        endText = String.format("%02d:%02d", endMinutes, endSeconds)
    } else {
        endText = String.format("%d:%02d", endMinutes, endSeconds)
    }

    Column(
        modifier = modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = modifier
                    .padding( end = 10.dp ),
                text = "$startText-\r\n$endText",
                color = Color(0xFF000000),
                fontSize = 13.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontName,
                fontStyle = FontStyle.Italic,
                lineHeight = 20.sp,
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 10.dp)
                    .shadow(
                        elevation = 8.dp,
                        spotColor = Color(0xFF000000),
                        ambientColor = Color(0xFF000000),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDADADA),
                        shape = RoundedCornerShape(size = 10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 15.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = exercise.getExercise().name,
                        color = Color(0xFF000000),
                        fontSize = 17.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.W600,
                        lineHeight = 20.sp,
                    )
                    Row (
                        modifier = modifier
                            .padding(top = 2.dp)
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Row(
                            modifier = modifier.weight(1f),
                        ){
                            Text(
                                text = "SONG: ",
                                color = Color(0xFF7400B8),
                                fontFamily = FontName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W600,
                            )
                            Text(
                                text = exercise.getSong(),
                                color = Color(0xFF000000),
                                fontFamily = FontName,
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                        Row(
                            modifier = modifier
                                .fillMaxHeight()
                                .height(IntrinsicSize.Min),
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.shuffle),
                                modifier = modifier
                                    .padding(end = 10.dp)
                                    .fillMaxHeight(),
                                contentDescription = "Shuffle button",
                                contentScale = ContentScale.FillHeight,
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun BottomBar(
    start: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            modifier = modifier
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .background(color = Color(0xFF7400B8), shape = RoundedCornerShape(size = 4.dp))
                .padding(start = 35.dp, end = 35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7400B8),
                contentColor = Color(0xFFFFFFFF),
            ),
            onClick = start,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "START ROUTINE",
                fontFamily = FontName,
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
            )
        }
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.svg),
            contentDescription = "Bottom Design",
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Preview(name = "Routine light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RoutineScreenPreview() {
    MusicTonedTheme {
        RoutineScreen(
            onNavigateToEditRoutine = {},
            onNavigateToRoutines = {},
            onNavigateToPlayer = {},
            routineID = 1
        )
    }
}