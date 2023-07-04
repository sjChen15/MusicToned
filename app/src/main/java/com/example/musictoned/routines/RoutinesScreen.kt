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
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.BottomBar
import com.example.musictoned.util.BottomNavPages
import com.example.musictoned.util.supportWideScreen

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun RoutinesScreen(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToEditRoutine: () -> Unit,
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
                        onNavigateToEditRoutine = onNavigateToEditRoutine
                    )
                }
            }
        )
    }

//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.Top
//        )


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

// TODO - Replace this placeholder with the components that you need
@Composable
private fun RoutinesContent(
    onNavigateToRoutine: (routineID: Int) -> Unit,
    onNavigateToEditRoutine: () -> Unit,
    //routines: List<Workout>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()

    ) {
            //routines.size + 1 (+1 for the add new button)
        items(2){
            RoutineBox(onNavigateToRoutine = onNavigateToRoutine)
        }
        items (1){
            AddNewRoutineBox(onNavigateToEditRoutine = onNavigateToEditRoutine)
        }

    }
//    Button(
//        onClick = onNavigateToRoutine,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 28.dp, bottom = 3.dp)
//    ) {
//        Text(
//            text = "Navigate to routine",
//            style = MaterialTheme.typography.titleSmall
//        )
//    }

}


@Composable             //routine: Workout
private fun RoutineBox(
    onNavigateToRoutine: (routineID: Int) -> Unit
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
                text = "Workout Title",
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge

            )
            Text(
                text = "Workout time",
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
            //button or clickable text
            ClickableText(
                text = AnnotatedString("START"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                ),
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp),
                onClick = {onNavigateToRoutine(10101)}
            )

            Divider(
                color = Color(81, 83, 181, 255),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.5.dp)
            )

            ClickableText(
                text = AnnotatedString("^"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                ),
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp),
                onClick = { onNavigateToRoutine(627) }
            )
                //button or clickabletext
        }
    }
}


@Composable
private fun AddNewRoutineBox(
    onNavigateToEditRoutine: () -> Unit
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
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable{onNavigateToEditRoutine()},
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Icon(
            modifier = Modifier
                .size(size = 50.dp)
                .background(color = Color.Transparent),
            imageVector = Icons.Default.Add,
            contentDescription = "Person Icon",
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
            onNavigateToSettings = {}
        )
    }
}