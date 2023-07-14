package com.example.musictoned.editRoutine

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.example.musictoned.R
import com.example.musictoned.addExercise.addExerciseScreen
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.AllWorkouts.saveWorkout
import com.example.musictoned.workoutcreation.ExerciseTempos
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise
import java.util.Locale


/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun EditRoutineScreen(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    onNavigateToAddExercise: () -> Unit,
) {

    var workout by remember { mutableStateOf(AllWorkouts.getWorkoutInProgress()) }

    var popupControl by remember { mutableStateOf(false) }

    var exercises = remember { mutableStateListOf<WorkoutExercise>() }

    exercises.addAll(workout.exercises.toList())

    Surface(modifier = Modifier
        .supportWideScreen()
    ) {
        if (popupControl) {
            Popup(
                alignment = Alignment.Center,
                offset = IntOffset(0, -40),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.75f)
                ){
                    addExerciseScreen(
                        onPopupChange = { popupControl = it },
                        returnExercise = {
                            val exerciseName = it
                            //add exercise to current workout
                            if (exerciseName != null.toString()){
                                workout.addExercise(WorkoutExercise(ExerciseTempos.getExercise(exerciseName)))
                                workout.deleteLastExercise()
                                exercises.clear()
                                exercises.addAll(workout.exercises)
                            }
                        }
                    )
                }

            }
        }
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
                .navigationBarsPadding(),
            backgroundColor = Color(0x00000000),
            topBar = {
                TopBar(
                    onNavigateToRoutine = onNavigateToRoutine,
                    workout = workout,
                )
            },
            bottomBar = {
                BottomBar(
                    modifier = Modifier.padding(top = 5.dp),
                    popupControl = popupControl,
                    onPopupChange = { popupControl = it },
                    onNavigateToAddExercise = onNavigateToAddExercise
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier.padding(innerPadding)){
                    key(exercises){
                        LazyColumn {
                            items(exercises) { exercise ->
                                var duration by remember { mutableStateOf(exercise.getLength()) }
                                Exercise( exercise = exercise,
                                    onMoveUp = {
                                        val index = workout.exercises.indexOf( it )
                                        if ( index != 0 ){
                                            workout.reorderExercise( it, index - 1 )
                                        }
                                        exercises.clear()
                                        exercises.addAll(workout.exercises)
                                    },
                                    onMoveDown = {
                                        val index = workout.exercises.indexOf( it )
                                        if ( index != workout.exercises.size - 1 ){
                                            workout.reorderExercise( it, index + 1 )
                                        }
                                        duration = exercises[exercises.indexOf(exercise)].getLength()
                                        exercises.clear()
                                        exercises.addAll(workout.exercises)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun TopBar(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    modifier: Modifier = Modifier,
    workout: Workout,
) {
    Column(
        modifier = modifier.padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 10.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp,),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row{
                Text(
                    modifier = modifier
                        .clickable {
                            onNavigateToRoutine(0)
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
                    fontFamily = FontName,
                    fontWeight = FontWeight.W700,
                )
            }
            Button(
                modifier = modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .height(40.dp)
                    .background(color = Color(0xFF5E60CE), shape = RoundedCornerShape(size = 8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E60CE),
                    contentColor = Color(0xFFFFFFFF),
                ),
                onClick = {
                    saveWorkout()
                    onNavigateToRoutine(workout.hashCode())
                }
            ){
                Text(
                    text = "Save",
                    fontFamily = FontName,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        }
        Divider(color = Color(0x69B4A7D6), thickness = 2.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Exercise(
    modifier: Modifier = Modifier,
    exercise: WorkoutExercise,
    onMoveUp: ( exercise: WorkoutExercise ) -> Unit,
    onMoveDown: ( exercise: WorkoutExercise ) -> Unit,
){
    var duration by remember { mutableStateOf(exercise.getLength().toString()) }
    val interactionSource = remember { MutableInteractionSource() }

    Column( modifier = modifier.padding(bottom=15.dp)){
        Column(
            modifier = modifier
                .fillMaxWidth()
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
                )
        ) {
            Row(
                modifier = modifier
                    .padding(top = 15.dp, bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column(){
                    Button(
                        onClick = {
                            onMoveUp( exercise )
                        }
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.drag_drop_icon),
                            modifier = modifier
                                .padding(end = 20.dp, start = 20.dp)
                                .height(20.dp),
                            contentDescription = "Move up",
                            contentScale = ContentScale.FillHeight,
                        )
                    }
                    Button(
                        onClick = {
                            onMoveDown( exercise )
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.drag_drop_icon),
                            modifier = modifier
                                .padding(end = 20.dp, start = 20.dp)
                                .height(20.dp),
                            contentDescription = "Move down",
                            contentScale = ContentScale.FillHeight,
                        )
                    }
                }
                Column(
                    modifier = modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(end = 20.dp)
                ){
                    Text(
                        text = exercise.getExercise().name,
                        color = Color(0xFF000000),
                        fontFamily = FontName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W600,
                    )
                    Row(
                        modifier = modifier
                            .padding(top = 15.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(
                            text = "Duration(s):",
                            fontSize = 15.sp,
                        )
                        BasicTextField(
                            value = duration,
                            onValueChange = {
                                duration = it },
                            modifier = Modifier
                                .height(30.dp)
                                .padding(start = 10.dp)
                                .fillMaxWidth(),
                            singleLine = true,
                            interactionSource = interactionSource,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        ) { innerTextField ->
                            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                                value = duration,
                                innerTextField = innerTextField,
                                enabled = true,
                                singleLine = true,
                                visualTransformation = VisualTransformation.None,
                                interactionSource = interactionSource,
                                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                                    top = 0.dp,
                                    bottom = 0.dp,
                                    start = 7.dp,
                                )
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(
                            text = "BPM Mode:",
                            fontSize = 15.sp,
                        )
                        DropdownMenu(
                            exercise = exercise,
                        )
                    }
                    Text(
                        modifier = modifier
                            .fillMaxWidth(),
                        text = "DELETE",
                        fontSize = 12.sp,
                        fontFamily = FontName,
                        color = Color(0xFF7400B8),
                        textAlign = TextAlign.End,
                        fontStyle = FontStyle.Italic,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    exercise: WorkoutExercise
) {
    val context = LocalContext.current
    val speed = arrayOf("Slow", "Average", "Fast")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(
        exercise.getBpmMode()
            .toString()
            .lowercase(Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 11.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )},
                modifier = Modifier,
                textStyle = TextStyle(
                    fontSize = 15.sp,
                ),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                speed.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    onNavigateToAddExercise: () -> Unit,
    popupControl: Boolean,
    onPopupChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            modifier = modifier
            .size(55.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5E60CE),
                contentColor = Color(0xFFFFFFFF),
            ),
            onClick = { onPopupChange( true ) },
            contentPadding = PaddingValues(1.dp),
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "+",
                fontFamily = FontName,
                fontSize = 35.sp,
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
fun EditRoutineScreenPreview() {
    MusicTonedTheme {
        com.example.musictoned.editRoutine.EditRoutineScreen(
            onNavigateToRoutine = {},
            onNavigateToAddExercise = {},
        )
    }
}