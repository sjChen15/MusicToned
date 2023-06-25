package com.example.musictoned.addExercise

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

var exercises = listOf<String>("Tricep Extension",
    "Hammer Curls",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
    "Barbell Bicep Curl",
)

@Composable
fun addExerciseScreen(
    onNavigateToEditRoutine: () -> Unit
) {
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
                TopBar(
                    onNavigateToEditRoutine = onNavigateToEditRoutine
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier
                    .padding(innerPadding),){
                   ExerciseList(
                       onNavigateToEditRoutine = onNavigateToEditRoutine,
                       exercises = exercises
                   )
                }
            }
        )
    }
}

@Composable
private fun TopBar(
    onNavigateToEditRoutine: () -> Unit,
    modifier: Modifier = Modifier
) {

    val exerciseType = listOf<String>("ARMS", "CHEST", "LEGS", "BACK", "CORE", "CARDIO")

    Column(
        modifier = modifier.padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 10.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp,)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "ADD EXERCISE",
                color = Color(0xFF5E60CE),
                fontSize = 30.sp,
                letterSpacing = 2.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontName,
                fontWeight = FontWeight.W700,
            )
            Image(
                modifier = modifier
                    .fillMaxHeight()
                    .clickable {
                        onNavigateToEditRoutine()
                    },
                painter = painterResource(id = R.drawable.exit),
                contentDescription = "Close Button",
                contentScale = ContentScale.FillHeight,
            )
        }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                exerciseType.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

    }
}

@Composable
private fun ExerciseList(
    exercises: List<String>,
    modifier: Modifier = Modifier,
    onNavigateToEditRoutine: () -> Unit,
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
                    .background(color = Color(0xFFF4F2F9), shape = RoundedCornerShape(size = 10.dp))
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
                        text = exercises[index],
                        fontFamily = FontName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                    )
                    Text(
                        modifier = modifier
                            .padding(top = 10.dp, bottom = 10.dp),
                        text = "Triceps",
                        fontFamily = FontName,
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Italic,
                    )
                    Button(
                        modifier = modifier
                            .shadow(elevation = 8.dp, spotColor = Color(0x1F000000), ambientColor = Color(0x1F000000))
                            .background(color = Color(0xFF9C27B0), shape = RoundedCornerShape(size = 4.dp))
                            .padding(start = 10.dp, end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF9C27B0),
                            contentColor = Color(0xFFFFFFFF),
                        ),
                        contentPadding = PaddingValues(0.dp),
                        onClick = onNavigateToEditRoutine,
                    ){
                        Text(
                            text = "ADD",
                            fontFamily = FontName,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.W600,
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Routine light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddExerciseScreenPreview() {
    MusicTonedTheme {
        com.example.musictoned.addExercise.addExerciseScreen(
            onNavigateToEditRoutine = {}
        )
    }
}
