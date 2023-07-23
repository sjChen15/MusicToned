package com.example.musictoned.routines

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.BottomWaves
import com.example.musictoned.util.BottomBar
import com.example.musictoned.util.BottomNavPages
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
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
            containerColor = Color(0x00000000),
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
            color = Color(0xFF5E60CE),
            fontSize = 24.sp,
            letterSpacing = 2.sp,
            fontFamily = FontName,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.W700,

            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 18.dp)
                .padding(top = 20.dp, bottom = 13.dp)
        )
        Divider(
            color = Color(0x69B4A7D6),
            thickness = 2.dp,
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, bottom = 0.dp)
        )
    }
}

@Composable
private fun RoutinesContent(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToEditRoutine: (routineID: Int) -> Unit,
) {
    val allWorkouts: SnapshotStateList<Workout> = if (LocalInspectionMode.current) {
        // Preview mode
        remember { previewWorkouts().toMutableStateList() }
    } else {
        // Production
       remember { AllWorkouts.getAllWorkouts().toMutableStateList() }
    }

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
            .offset(x = 0.dp, y = 5.dp)
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
            .aspectRatio(1.5f),
        verticalArrangement = Arrangement.SpaceEvenly
    )

    {

        Column {

            Text(
                text = workout.name,
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontName,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(600)
            )
            Text(
                text = workout.getWorkoutDurationHourFormat(),
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontName,
                fontStyle = FontStyle.Italic,
            )
        }

        Row(
            modifier = Modifier
                .height(28.dp)
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
                    .fillMaxWidth()
                    .weight(0.4f)
                    .clickable { expanded = !expanded }
            ){
                /**
                 * Reference: https://alexzh.com/jetpack-compose-dropdownmenu/
                 */
                Box {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = Color.White
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        onClick = {
                            onNavigateToEditRoutine(workout.hashCode())
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
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
            .offset(x = 0.dp, y = 5.dp)
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
            .aspectRatio(1.5f)
            .clickable { onNavigateToEditRoutine(0) },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Icon(
            modifier = Modifier
                .size(size = 30.dp)
                .padding(bottom = 0.dp)
                .background(color = Color.Transparent),
            imageVector = Icons.Default.Add,
            contentDescription = "Plus Icon",
            tint = Color(94,96,206,255)
        )

        Text(
            text = "Create \nNew Routine",
            color = Color(107,109,209,255),
            fontSize = 16.sp,
            fontFamily = FontName,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
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

    for (i in 2 downTo  1){
        previewAllWorkouts.add(previewWorkout)
    }

    return previewAllWorkouts

}