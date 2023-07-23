package com.example.musictoned.editRoutine

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.musictoned.R
import com.example.musictoned.addExercise.AddExerciseScreen
import com.example.musictoned.spotify.SpotifyConnect
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.TextFieldRegex
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.AllWorkouts.saveWorkout
import com.example.musictoned.workoutcreation.BpmMode
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
    routineID: Int?
) {
    val workout: MutableState<Workout> = if (LocalInspectionMode.current) {
        // Preview mode
        remember { mutableStateOf(Workout("New Workout")) }
    } else if (routineID != null) {
        // Existing workout
        remember { mutableStateOf(AllWorkouts.getWorkout(routineID)) }
    } else {
        // New workout
        remember { mutableStateOf(AllWorkouts.getWorkoutInProgress()) }
    }

    var popupControl by remember { mutableStateOf(false) }

    val exercises = remember { mutableStateListOf<WorkoutExercise>() }

    val swapped = remember { mutableStateOf(false) }

    exercises.addAll(workout.component1().exercises.toList())

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
                    AddExerciseScreen(
                        onPopupChange = { popupControl = it },
                        returnExercise = {
                            val exerciseName = it
                            //add exercise to current workout
                            if (exerciseName != null.toString()){
                                workout.component1().addExercise(WorkoutExercise(ExerciseTempos.getExercise(exerciseName)))
                                exercises.clear()
                                exercises.addAll(workout.component1().exercises)
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
            containerColor = Color(0x00000000),
            topBar = {
                TopBar(
                    onNavigateToRoutine = onNavigateToRoutine,
                    workout = workout.component1(),
                )
            },
            bottomBar = {
                BottomBar(
                    modifier = Modifier.padding(top = 5.dp),
                    onPopupChange = { popupControl = it },
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier.padding(innerPadding)){
                    Exercises(
                        modifier = Modifier,
                        exercises = exercises,
                        deleteExercise = {
                            workout.component1().deleteExercise(it)
                            exercises.clear()
                            exercises.addAll(workout.component1().exercises)},
                        swapped = swapped,
                        onMoveUp = {
                            val index = workout.component1().exercises.indexOf(it)
                            if (index != 0) {
                                workout.component1().reorderExercise(it, index - 1)
                            }
                            exercises.clear()
                            exercises.addAll(workout.component1().exercises)
                            swapped.value = true
                        },
                        onMoveDown = {
                            val index = workout.component1().exercises.indexOf(it)
                            if (index != workout.component1().exercises.size - 1) {
                                workout.component1().reorderExercise(it, index + 1)
                            }
                            exercises.clear()
                            exercises.addAll(workout.component1().exercises)
                            swapped.value = true
                        },
                    )
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

    val context = LocalContext.current
    var text by remember { mutableStateOf(workout.name ) }

    Column(
        modifier = modifier
            .padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 10.dp)
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
                            onNavigateToRoutine(workout.hashCode())
                        },
                    text = "< ",
                    color = Color(0xFF5E60CE),
                    fontSize = 24.sp,
                    letterSpacing = 2.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.W700,
                )
                BasicTextField(
                    modifier = modifier
                        .width(IntrinsicSize.Min),
                    value = text.uppercase(),
                    onValueChange = {
                        if (it.length <= 11){
                            text = it
                            workout.name = text
                        }
                     },
                    textStyle = TextStyle(
                        color = Color(0xFF5E60CE),
                        fontSize = 24.sp,
                        letterSpacing = 2.sp,
                        fontFamily = FontName,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.W700,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true
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
                    if(workout.exercises.size > 0){

                        //Should we just make it so that exercises have a default song?
                        for(ex in workout.exercises){
                            if (ex.getSong() == ""){
                                ex.setSongByBPM(ex.getBpmMode())
                                //TODO - (DisableSpotifySongs) if you do not need to work with spotify, replace the above line with the below line:
                                //ex.setSong("Despacito", "")
                            }
                        }

                        saveWorkout(workout)
                        onNavigateToRoutine(workout.hashCode())
                    } else {
                        Toast.makeText(context, "Must Create an Exercise", Toast.LENGTH_SHORT).show()
                    }
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

@Composable
private fun Exercises(
    modifier: Modifier,
    exercises: List<WorkoutExercise>,
    deleteExercise: (WorkoutExercise) -> Unit,
    swapped: MutableState<Boolean>,
    onMoveUp: ( exercise: WorkoutExercise ) -> Unit,
    onMoveDown: ( exercise: WorkoutExercise ) -> Unit,
){
    LazyColumn(
        modifier = modifier
            .padding( start = 15.dp, end = 15.dp)
    ) {
        items(exercises) { exercise ->
            var isLast = false
            if (exercises.indexOf(exercise) == exercises.size - 1){
                isLast = true
            }
            Exercise(
                exercise = exercise,
                deleteExercise = deleteExercise,
                swapped = swapped,
                onMoveUp = onMoveUp,
                onMoveDown = onMoveDown,
                onAckSwap = { swapped.value = false },
                isLast = isLast,
            )
        }
    }
}

@Composable
fun Exercise(
    modifier: Modifier = Modifier,
    exercise: WorkoutExercise,
    deleteExercise: (WorkoutExercise) -> Unit,
    swapped: MutableState<Boolean>,
    isLast: Boolean,
    onMoveUp: ( exercise: WorkoutExercise ) -> Unit,
    onMoveDown: ( exercise: WorkoutExercise ) -> Unit,
    onAckSwap: () -> Unit,
){
    var duration by remember { mutableStateOf(exercise.getLength().toString()) }
    val interactionSource = remember { MutableInteractionSource() }

    if (swapped.value){
        duration = exercise.getLength().toString()
    }

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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                   Image(
                       painter = painterResource(id = R.drawable.arrow_up),
                       modifier = modifier
                           .padding(end = 5.dp, start = 5.dp)
                           .height(30.dp)
                           .clickable { onMoveUp(exercise) },
                       contentDescription = "Move up",
                       contentScale = ContentScale.FillHeight,
                   )
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down),
                        modifier = modifier
                            .padding(end = 5.dp, start = 5.dp)
                            .height(30.dp)
                            .clickable { onMoveDown(exercise) },
                        contentDescription = "Move down",
                        contentScale = ContentScale.FillHeight,
                    )
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
                                if(duration.isEmpty() || duration.matches(TextFieldRegex.wholeNumberRegex)) {
                                    duration = it
                                    exercise.setLength(if(duration.isEmpty()) 0 else duration.toLong())
                                }
                            },
                            modifier = Modifier
                                .height(30.dp)
                                .padding(start = 10.dp)
                                .fillMaxWidth(),
                            singleLine = true,
                            interactionSource = interactionSource,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        ) { innerTextField ->
                            OutlinedTextFieldDefaults.DecorationBox(
                                value = duration,
                                innerTextField = innerTextField,
                                enabled = true,
                                singleLine = true,
                                visualTransformation = VisualTransformation.None,
                                interactionSource = interactionSource,
                                colors = OutlinedTextFieldDefaults.colors(),
                                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                                    start = 15.dp,
                                    top = 0.dp,
                                    bottom = 0.dp,
                                ),
                                container = {
                                    OutlinedTextFieldDefaults.ContainerBox(enabled=true, isError=false, interactionSource, OutlinedTextFieldDefaults.colors())
                                },
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
                            swapped = swapped,
                            isLast = isLast,
                            onAckSwap = onAckSwap
                        )
                    }
                    TextButton(
                        onClick = {
                            deleteExercise(exercise)
                        }
                    ) {
                        Text(
                            modifier = modifier
                            .fillMaxWidth(),
                            text = "DELETE",
                            fontSize = 12.sp,
                            fontFamily = FontName,
                            color = Color(0xFF7400B8),
                            textAlign = TextAlign.End,
                            fontStyle = FontStyle.Italic,)
                    }
//                    Text(
//                        modifier = modifier
//                            .fillMaxWidth(),
//                        text = "DELETE",
//                        fontSize = 12.sp,
//                        fontFamily = FontName,
//                        color = Color(0xFF7400B8),
//                        textAlign = TextAlign.End,
//                        fontStyle = FontStyle.Italic,
//                    )
                }
            }
        }
    }
}

fun formatBPMToProperString(bpm: BpmMode): String {
    return bpm.toString().lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

@Composable
fun DropdownMenu(
    exercise: WorkoutExercise,
    swapped: MutableState<Boolean>,
    isLast: Boolean,
    onAckSwap: () -> Unit,
) {
    val speed = arrayOf(BpmMode.SLOW, BpmMode.AVERAGE, BpmMode.FAST)
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(
        formatBPMToProperString(exercise.getBpmMode())
            ) }
    if (swapped.value){
        selectedText = exercise.getBpmMode()
            .toString()
            .lowercase(Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        if ( isLast ){
            onAckSwap()
        }
    }

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
//            BasicTextField(
//                value = selectedText,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(
//                    expanded = expanded
//                )},
//                modifier = Modifier
//                    .menuAnchor()
//                    .height(50.dp)
//                    .padding(5.dp),
//                textStyle = TextStyle(
//                    fontSize = 15.sp,
//                ),
//                colors = ExposedDropdownMenuDefaults.textFieldColors(
//                    unfocusedContainerColor = Color.White,
//                    focusedContainerColor = Color.White
//                ),
//            ) { innerTextField ->
//                OutlinedTextFieldDefaults.DecorationBox(
//                    value = selectedText
//                )
//
//            }
            val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(30.dp)
            ) { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = selectedText,
                    innerTextField = innerTextField,
                    enabled = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )},
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(start=15.dp, top=0.dp, end=0.dp, bottom=0.dp), // this is how you can remove the padding
                )
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                speed.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = formatBPMToProperString(item)) },
                        onClick = {
                            selectedText = formatBPMToProperString(item)
                            expanded = false
                            //if the user changes the speed of their exercise
                            if(item != exercise.getBpmMode()){
                                exercise.setSongByBPM(item)
                                //TODO - (DisableSpotifySongs) if you do not need to work with spotify, replace the above line with the below line:
                                //exercise.setSong("Despacito", "")
                            }
                            exercise.setBpmMode(item)
                        },
                        modifier = Modifier.height(30.dp)
                    )
                }

            }
        }
    }
}

@Composable
private fun BottomBar(
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
        EditRoutineScreen(
            onNavigateToRoutine = {},
            routineID = null
        )
    }
}

@Preview(name = "Dropdown", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DropdownPreview() {
    DropdownMenu(
        exercise=WorkoutExercise(
            exercise=com.example.musictoned.workoutcreation.Exercise(
                name="Bench press"
            ),
            length=30
        ),
        swapped = remember { mutableStateOf(false) },
        isLast = false,
        onAckSwap = {}
    )
}