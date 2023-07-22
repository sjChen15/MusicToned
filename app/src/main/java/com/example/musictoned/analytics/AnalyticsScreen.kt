package com.example.musictoned.analytics

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.profile.Profile
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.util.BottomBar
import com.example.musictoned.util.BottomNavPages
import java.time.format.TextStyle

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun AnalyticsScreen(
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
                    currentPage = BottomNavPages.ANALYTICS
                )
            },
            content = { innerPadding ->
                Box ( modifier = Modifier.padding(innerPadding)){
                    AnalyticsContent()
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
            text = "ANALYTICS",
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
private fun AnalyticsContent(

) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()

    ){
        items(1){
            StreaksBox()
        }
        items(1){
            CalorieGoalBox()
        }
    }
}

@Composable
private fun StreaksBox(){
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
                color = Color(0x45B6E8F8),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(color = Color(208, 240, 251,255), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 1.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Workout Streak",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFF484848),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = Analytics.getWorkoutStreakFromHistory().toString(),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFF7400B8),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "Days Straight",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFFA9A9A9),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
private fun CalorieGoalBox(){
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
                color = Color(0x45B6E8F8),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(color = Color(208, 240, 251,255), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 1.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        Text(
            text = "Calorie Goal",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFF484848),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = Analytics.getCaloriesBurned().toString(),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFF7400B8),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            //TODO get goal
            text = "/ ${Profile.profile.calorieGoal} kCal",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFFA9A9A9),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview(name = "Analytics light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AnalyticsScreenPreview() {
    MusicTonedTheme {
        AnalyticsScreen(
            onNavigateToRoutines = {},
            onNavigateToAnalytics = {},
            onNavigateToSettings = {}
        )
    }
}