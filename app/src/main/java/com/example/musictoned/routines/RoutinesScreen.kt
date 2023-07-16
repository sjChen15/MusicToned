package com.example.musictoned.routines

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.BottomWaves
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.BottomBar
import com.example.musictoned.util.BottomNavPages
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.Workout
import java.util.LinkedList


/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun RoutinesScreen(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToEditRoutine: (routineID: Int) -> Unit,
    onNavigateToRoutines: (charOffset: Int) -> Unit,
    onNavigateToAnalytics: (charOffset: Int) -> Unit,
    onNavigateToSettings: (charOffset: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .supportWideScreen()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
                .navigationBarsPadding(),
            backgroundColor = Color.Transparent,
            contentColor = Color.Transparent,
            topBar = {
                TopBar()
            },
            bottomBar = {
                BottomBar(
                    onNavigateToRoutines = onNavigateToRoutines,
                    onNavigateToAnalytics = onNavigateToAnalytics,
                    onNavigateToSettings = onNavigateToSettings,
                    currentPage = BottomNavPages.ROUTINES
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier.padding(innerPadding)){
                    RoutinesContent(
                        onNavigateToRoutine = onNavigateToRoutine,
                        onNavigateToEditRoutine = onNavigateToEditRoutine,
                    )
                }
            }
        )
    }

}

@Composable
private fun TopBar(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "ROUTINES",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = Color(94,96,206,255),
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight(500),
            letterSpacing = 0.46.sp,
            modifier = Modifier
                .padding(start = 24.dp)
                .padding(top = 12.dp)
        )
        Divider(
            color = Color(94,96,206,255),
            thickness = 1.dp,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 4.dp)
        )

    }
}

@Composable
private fun RoutinesContent(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToEditRoutine: (routineID: Int) -> Unit,
) {
            //ACTUAL FUNCTIONALITY
    //var allWorkouts = remember { mutableStateListOf<Workout>() }
    val allWorkouts = AllWorkouts.getAllWorkouts().toMutableStateList()

            //FOR PREVIEW ONLY
    //val allWorkouts = previewWorkouts().toMutableStateList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()

    ) {
        //routines.size + 1 (+1 for the add new button)
        items(allWorkouts.size){
                index ->
            RoutineBox( onNavigateToRoutine = onNavigateToRoutine,
                onNavigateToEditRoutine = onNavigateToEditRoutine,
                workout = allWorkouts[index],
                allWorkouts = allWorkouts
            )
        }
        items (1){
            AddNewRoutineBox(onNavigateToEditRoutine = onNavigateToEditRoutine)
        }

    }

    BottomWaves(withBottomBar = true)

//    Button(onClick = { AllWorkouts.deleteWorkout(workout = allWorkouts[0]) }) {
//        Text(text = AllWorkouts.getAllWorkouts().size.toString())
//    }

}


@Composable
private fun RoutineBox(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToEditRoutine: (routineID: Int) -> Unit,
    workout: Workout,
    allWorkouts: SnapshotStateList<Workout>
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .offset(x = 0.dp, y = 20.dp)
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0x3BA079D2),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(color = Color(230, 230, 253, 255), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        verticalArrangement = Arrangement.SpaceEvenly
    )

    {

        Column {

            Text(
                text = workout.name,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = workout.getWorkoutDurationHourFormat(),
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .background(
                    color = Color(94, 96, 206, 255),
                    shape = RoundedCornerShape(2.5.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly


        ) {

            //box with onclick event to start workout
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable { onNavigateToRoutine(workout.hashCode()) }
            ){
                Text(
                    text = AnnotatedString("START"),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                )

            }

            Divider(
                color = Color(81, 83, 181, 255),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.5.dp)
            )

            /* DropdownMenu reference: https://alexzh.com/jetpack-compose-dropdownmenu/ */

            var expanded by remember { mutableStateOf(false) }

            //box with onclick event to enable dropdown
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f)
                    .clickable { expanded = !expanded }
            ){
                Icon(
                    modifier = Modifier
                        .size(size = 25.dp)
                        .background(color = Color.Transparent)
                        .align(Alignment.Center),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop down arrow Icon",
                    tint = Color.White
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    ClickableText(
                        text = AnnotatedString("Edit"),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        ),
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp),
                        onClick = {
                            onNavigateToEditRoutine(workout.hashCode())
                            expanded = false
                        }
                    )

                    ClickableText(
                        text = AnnotatedString("Delete"),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        ),
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp),
                        onClick = {
                                    AllWorkouts.deleteWorkout(workout)
                                    allWorkouts.remove(workout)
                                    expanded = false
                                    //onNavigateToRoutines(0)
                        }
                    )

                } //end drop down menu

            } //end box

        } //end row
    }
}


@Composable
private fun AddNewRoutineBox(
    onNavigateToEditRoutine: (routineID: Int) -> Unit
){
    val stroke = Stroke(
        width = 6f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 12f), 0f)
    )
    Column(
        modifier = Modifier
            .padding(10.dp)
            .offset(x = 0.dp, y = 20.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color(107, 109, 209, 255),
                    style = stroke,
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 5.dp, vertical = 0.dp)
            .fillMaxSize()
            .aspectRatio(1.06f)
            .clickable { onNavigateToEditRoutine(null?.toInt() ?: 0) },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Icon(
            modifier = Modifier
                .size(size = 50.dp)
                .background(color = Color.Transparent),
            imageVector = Icons.Default.Add,
            contentDescription = "Plus Icon",
            tint = Color(94,96,206,255)
        )

        Text(
            text = "Create New Routine",
            color = Color(107,109,209,255),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
        )
    }
}

@Preview(name = "Routines light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RoutinesScreenPreview() {
    MusicTonedTheme {
        RoutinesScreen(
            onNavigateToRoutine = {},
            onNavigateToEditRoutine = {},
            onNavigateToRoutines = {},
            onNavigateToAnalytics = {},
            onNavigateToSettings = {},
        )
    }
}


fun previewWorkouts(): ArrayList<Workout>{

    val previewAllWorkouts = ArrayList<Workout>()

    val previewWorkout = Workout("fake workout", LinkedList())

    for (i in 20 downTo  1){
        previewAllWorkouts.add(previewWorkout)
    }

    return previewAllWorkouts

}