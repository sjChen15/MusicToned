package com.example.musictoned.routines

import android.content.res.Configuration
import android.graphics.BlurMaskFilter
import android.graphics.Typeface.NORMAL
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.workoutcreation.Workout
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun RoutinesScreen(
    onNavigateToRoutine: () -> Unit,
    onNavigateToSpotifyBeta: () -> Unit
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
                TopBar()
            },
            bottomBar = {
                BottomBar(
                    //modifier = Modifier.padding(top = 5.dp),
                    //start = onNavigateToEditRoutine
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier.padding(innerPadding)){
                    RoutinesContent(
                        onNavigateToRoutine = onNavigateToRoutine,
                        onNavigateToSpotifyBeta = onNavigateToSpotifyBeta
                    )
                }
            }
        )
    }
//    Surface(modifier = Modifier.supportWideScreen()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.Top
//        ) {
//            TopBar()
//            RoutinesContent(
//                onNavigateToRoutine = onNavigateToRoutine,
//                onNavigateToSpotifyBeta = onNavigateToSpotifyBeta
//            )
//        }
//        Column (
//            modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.Bottom
//        ){
//            BottomBar()
//        }



}

@Composable
private fun TopBar(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ) {
        Text(
            text = "Routines",
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
                .padding(start = 24.dp)
                .padding(end = 24.dp)
        )

    }
}

// TODO - Replace this placeholder with the components that you need
@Composable
private fun RoutinesContent(
    onNavigateToRoutine: () -> Unit,
    onNavigateToSpotifyBeta: () -> Unit,
    //routines: List<Workout>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()

    ) {
            //routines.size + 1 (+1 for the add new button)
        items(10){
            RoutineBox()
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
private fun RoutineBox(){

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
            Text(
                text = "START",
                color = Color.White,
                modifier = Modifier
                    .weight(1.5f),
                textAlign = TextAlign.Center
            )
            Divider(
                color = Color(81,83,181,255),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.5.dp)
            )
            Text(
                text = "^",
                color = Color.White,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            //button or clickabletext
        }
    }

}

@Composable
private fun BottomBar(){

    val paddingOffset = 15.dp

    Column(modifier = Modifier.background(color = Color.Transparent)) {
        Image(
            painter = painterResource(id = R.drawable.routines_waves),
            contentDescription = "Routines Waves",
            alignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 0.dp, y = 1.dp)
                .background(color = Color.Transparent),
            contentScale = ContentScale.FillWidth,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(116, 0, 184, 255))
                .height(40.dp)
        ) {
            ClickableText(
                text = AnnotatedString("Routines"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                ),
                modifier = Modifier
                    .padding(bottom = paddingOffset),
                onClick = {}
            )
            ClickableText(
                text = AnnotatedString("Analytics"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                ),
                modifier = Modifier
                    .padding(bottom = paddingOffset),
                onClick = {}
            )
            ClickableText(
                text = AnnotatedString("Settings"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                ),
                modifier = Modifier
                    .padding(bottom = paddingOffset),
                onClick = {})

        }
    }


//    Box(
//        modifier = Modifier
//            .fillMaxWidth().background(color = Color.Transparent)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.routines_waves),
//            contentDescription = "Routines Waves",
//            alignment = Alignment.BottomCenter,
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter),
//            contentScale = ContentScale.FillWidth,
//        )
//        Row(
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(15.dp)
//        ) {
//            Button(
//                onClick = { /*TODO*/ }
//            ) {
//                Text(text = "BUTTON 1")
//            }
//        }
//    }

}



@Preview(name = "Routines light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Routines dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RoutinesScreenPreview() {
    MusicTonedTheme {
        RoutinesScreen(
            onNavigateToRoutine = {},
            onNavigateToSpotifyBeta = {}
        )
    }
}