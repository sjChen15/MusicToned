package com.example.musictoned.addExercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.Exercise
import com.example.musictoned.workoutcreation.ExerciseTempos
import java.util.Locale

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun AddExerciseScreen(
    returnExercise: (exerciseName: String) -> Unit,
    onPopupChange: (Boolean) -> Unit,
) {
    val currentExerciseType =  remember { mutableStateOf( "ARMS" ) }

    var exercises: List<Exercise> = if (LocalInspectionMode.current) {
        // Preview mode
        listOf(Exercise(name="Triceps Extension", target=listOf("triceps")))
    } else {
        // Production
        ExerciseTempos.getExercises(currentExerciseType.value)
    }

    Surface(modifier = Modifier
        .supportWideScreen()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF), RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color(0x68000000),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .navigationBarsPadding(),
            containerColor = Color(0x00000000),
            topBar = {
                TopBar(
                    onPopupChange = onPopupChange,
                    onExerciseTypeChange = {
                        currentExerciseType.value = it.value
                        exercises = ExerciseTempos.getExercises(currentExerciseType.value)
                    }
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier
                    .padding(innerPadding),){
                   ExerciseList(
                       returnExercise = returnExercise,
                       onPopupChange = onPopupChange,
                       exercises = exercises,

                   )
                }
            }
        )
    }
}

@Composable
private fun TopBar(
    onPopupChange: (Boolean) -> Unit,
    onExerciseTypeChange: (MutableState<String>) -> Unit,
    modifier: Modifier = Modifier
) {

    val exerciseType = listOf("ARMS", "CHEST", "LEGS", "BACK", "CORE", "CARDIO")
    val currentExerciseType =  remember { mutableStateOf( "ARMS" ) }

    Column(
        modifier = modifier.padding(start = 23.dp, end = 23.dp, top = 20.dp, bottom = 10.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "ADD EXERCISE",
                color = Color(0xFF5E60CE),
                fontSize = 25.sp,
                letterSpacing = 2.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontName,
                fontWeight = FontWeight.W700,
            )
            Image(
                modifier = modifier
                    .fillMaxHeight()
                    .clickable {
                        onPopupChange(false)
                    },
                painter = painterResource(id = R.drawable.exit),
                contentDescription = "Close Button",
                contentScale = ContentScale.FillHeight,
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                modifier = modifier
                    .fillMaxHeight()
                    .clickable {
                        if (exerciseType.indexOf(currentExerciseType.value) != 0) {
                            currentExerciseType.value =
                                exerciseType[exerciseType.indexOf(currentExerciseType.value) - 1]

                        } else {
                            currentExerciseType.value = exerciseType[exerciseType.size - 1]
                        }
                        onExerciseTypeChange(currentExerciseType)
                    },
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Left",
                contentScale = ContentScale.FillHeight,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .horizontalScroll(rememberScrollState())
            ) {
                exerciseType.forEach { item ->
                    print( item + currentExerciseType.toString() )
                    if ( currentExerciseType.value == item ){
                        ClickableText(
                            text = AnnotatedString(text = item),
                            modifier = modifier
                                .padding( end = 27.dp ),
                            onClick = {
                                currentExerciseType.value = item
                                onExerciseTypeChange(currentExerciseType)

                            },
                            style = TextStyle(
                                color = Color( 0xFF7400B8),
                                fontWeight = FontWeight(700),
                                textDecoration = TextDecoration.Underline,
                                fontSize = 19.sp
                            ),
                        )
                    } else {
                        ClickableText(
                            text = AnnotatedString(text = item),
                            modifier = modifier
                                .padding( end = 25.dp ),
                            onClick = {
                                currentExerciseType.value = item
                                onExerciseTypeChange(currentExerciseType)
                            },
                            style = TextStyle(
                                color = Color( 0x99000000),
                                fontSize = 19.sp,
                                fontWeight = FontWeight(600),
                            ),
                        )
                    }
                }
            }
            Image(
                modifier = modifier
                    .fillMaxHeight()
                    .clickable {
                        if (exerciseType.indexOf(currentExerciseType.value) != exerciseType.size - 1) {
                            currentExerciseType.value =
                                exerciseType[exerciseType.indexOf(currentExerciseType.value) + 1]

                        } else {
                            currentExerciseType.value = exerciseType[0]
                        }
                        onExerciseTypeChange(currentExerciseType)
                    },
                painter = painterResource(id = R.drawable.forward),
                contentDescription = "Right",
                contentScale = ContentScale.FillHeight,
            )
        }
    }
}

@Composable
private fun ExerciseList(
    exercises: List<Exercise>,
    modifier: Modifier = Modifier,
    onPopupChange: (Boolean) -> Unit,
    returnExercise: (exerciseName: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 25.dp,
            top = 5.dp,
            end = 25.dp,
            bottom = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(exercises.size) { index ->
            Box(
                modifier = modifier
                    .background(color = Color(0xFFE6E6FD), shape = RoundedCornerShape(size = 10.dp))
                    .aspectRatio(1f)
            ){
                Column(
                    modifier = modifier
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = exercises[index].name,
                        fontFamily = FontName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                    )
                    Text(
                        modifier = modifier
                            .padding(top = 10.dp, bottom = 10.dp),
                        text = java.lang.String.join( ", ", exercises[index].target )
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                        fontFamily = FontName,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                    )
                    Button(
                        modifier = modifier
                            .shadow(
                                elevation = 8.dp,
                                spotColor = Color(0x1F000000),
                                ambientColor = Color(0x1F000000)
                            )
                            .background(
                                color = Color(0xFF5E60CE),
                                shape = RoundedCornerShape(size = 4.dp)
                            )
                            .padding(start = 10.dp, end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5E60CE),
                            contentColor = Color(0xFFFFFFFF),
                        ),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            onPopupChange(false)
                            returnExercise(exercises[index].name)
                        },
                    ){
                        Text(
                            text = "ADD",
                            fontFamily = FontName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W600,
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Add exercise")
@Composable
fun AddExerciseScreenPreview() {
    MusicTonedTheme {
        AddExerciseScreen(
            returnExercise = {},
            onPopupChange = {}
        )
    }
}
