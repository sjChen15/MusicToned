package com.example.musictoned.analytics

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.profile.Profile
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.util.BottomBar
import com.example.musictoned.util.BottomNavPages

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
            containerColor = Color.Transparent,
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
                    Box( modifier = Modifier.padding(innerPadding)){
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
private fun AnalyticsContent(

) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 15.dp),
        ){
            items(1){
                StreaksBox()
            }
            items(1){
                CalorieGoalBox()
            }
            items(1, span = { GridItemSpan(2) }){
            BarChart(values = Analytics.getRecentActivityHours())
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
            .background(color = Color(208, 240, 251, 255), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 1.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Workout Streak",
            style = TextStyle(
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
            style = TextStyle(
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
            style = TextStyle(
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
            .background(color = Color(208, 240, 251, 255), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 1.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        Text(
            text = "Calorie Goal",
            style = TextStyle(
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
            style = TextStyle(
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontStyle = FontStyle.Normal,
                color = Color(0xFF7400B8),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "/ ${Profile.profile.calorieGoal} kCal",
            style = TextStyle(
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
//Ref: https://betterprogramming.pub/custon-charts-in-android-using-jetpack-compose-87b395c1d515
@Composable
private fun BarChart(
    modifier: Modifier = Modifier,
    values: List<Float>,
    maxHeight: Dp = 100.dp
) {
    val borderColor = MaterialTheme.colorScheme.primary
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }
    //val days = listOf("Sun", "Mon", "Tue", "Wed","Thu","Fri","Sat")

    Column(Modifier.padding(all = 20.dp)){
        Text(
            text = "Recent Activity (hr)",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(700),
                color = Color(0xFF5E60CE),
            ),
            modifier = Modifier.padding(all = 10.dp)

        )
        Row(
            modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    .height(maxHeight)
                    .drawBehind {
                        // draw X-Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
            ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            values.forEach { item ->
                Bar(
                    value = item,
                    color = MaterialTheme.colorScheme.primary,
                    maxHeight = maxHeight
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 11.dp),
            text = "Sun     Mon     Tue     Wed     Thu      Fri        Sat",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(700),
                color = Color(0xFF484848),
            )
        )
//        Row(
//            modifier = modifier.then(
//                Modifier
//                    .fillMaxWidth()
//                    .height(20.dp)
//            ),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.Bottom
//        ){
//            days.forEach{item ->
//                Text(
//                    modifier = Modifier.padding(horizontal = 11.dp),
//                    text = item,
//                    style = TextStyle(
//                        fontSize = 16.sp,
//                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                        fontWeight = FontWeight(700),
//                        color = Color(0xFF484848),
//                    )
//                )
//            }
//        }
    }

}

@Composable
private fun RowScope.Bar(
    value: Float,
    color: Color,
    maxHeight: Dp
) {

    val itemHeight = remember(value) { value * maxHeight.value/10 }

    Spacer(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .height(itemHeight.dp)
            .weight(1f)
            .background(color)
    )

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