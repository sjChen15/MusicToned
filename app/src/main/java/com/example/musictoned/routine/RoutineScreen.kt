package com.example.musictoned.routine

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun RoutineScreen(
    onNavigateToRoutines: () -> Unit
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
        ) {
            Placeholder(
                onNavigateToRoutines = onNavigateToRoutines
            )
        }
    }
}

// TODO - Replace this placeholder with the components that you need
@Composable
private fun Placeholder(
    onNavigateToRoutines: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Text(
            text = "Placeholder for routine screen. Here we will either be viewing an existing routine or we may be creating a new one. There should be an edit button if we're viewing an existing one. There should also be a start button if we're viewing an existing one",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
    Button(
        onClick = onNavigateToRoutines,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 3.dp)
    ) {
        Text(
            text = "Navigate to routines",
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(name = "Routine light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Routine dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RoutineScreenPreview() {
    MusicTonedTheme {
        RoutineScreen(
            onNavigateToRoutines = {}
        )
    }
}