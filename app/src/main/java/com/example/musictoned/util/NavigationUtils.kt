package com.example.musictoned.util

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.BOTTOM_BAR_HEIGHT
import com.example.musictoned.ui.theme.MusicTonedTheme

enum class BottomNavPages {
    ROUTINES,
    ANALYTICS,
    SETTINGS
}

/**
 * Bottom navigation bar with background
 */
@Composable
fun BottomBar(
    currentPage: BottomNavPages,
    onNavigateToRoutines: (charOffset: Int) -> Unit,
    onNavigateToAnalytics: (charOffset: Int) -> Unit,
    onNavigateToSettings: (charOffset: Int) -> Unit
){

    val paddingOffset = 0.dp

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(116, 0, 184, 255))
            .height(BOTTOM_BAR_HEIGHT.dp)
    ) {
        ClickableText(
            text = AnnotatedString("ROUTINES"),
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                textDecoration = if (currentPage === BottomNavPages.ROUTINES) TextDecoration.Underline else null
            ),
            modifier = Modifier
                .padding(paddingOffset),
            onClick = onNavigateToRoutines
        )
        ClickableText(
            text = AnnotatedString("ANALYTICS"),
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                textDecoration = if (currentPage === BottomNavPages.ANALYTICS) TextDecoration.Underline else null
            ),
            modifier = Modifier
                .padding(paddingOffset),
            onClick = onNavigateToAnalytics
        )
        ClickableText(
            text = AnnotatedString("SETTINGS"),
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                textDecoration = if (currentPage === BottomNavPages.SETTINGS) TextDecoration.Underline else null
            ),
            modifier = Modifier
                .padding(paddingOffset),
            onClick = onNavigateToSettings
        )
    }
}

@Preview(name = "Bottom bar light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BottomBarPreview() {
    MusicTonedTheme {
        BottomBar(
            onNavigateToRoutines = {},
            onNavigateToAnalytics = {},
            onNavigateToSettings = {},
            currentPage = BottomNavPages.SETTINGS
        )
    }
}