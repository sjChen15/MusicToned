package com.example.musictoned.yourGoals

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.welcome.BottomBranding

@Composable
fun YourGoalsScreen(
    onClickFinish: () -> Unit,
    viewModel: YourGoalsViewModel
) {
    Surface(modifier = Modifier
        .supportWideScreen()
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            Questionnaire(
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.padding(25.dp))
            Button(
                onClick = onClickFinish,
                shape = RoundedCornerShape(size = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E60CE)),
                modifier = Modifier
                    .offset(x = 78.dp, y = 140.dp)
                    .shadow(
                        elevation = 1.dp,
                        spotColor = Color(0x33000000),
                        ambientColor = Color(0x33000000)
                    )
                    .shadow(
                        elevation = 2.dp,
                        spotColor = Color(0x24000000),
                        ambientColor = Color(0x24000000)
                    )
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color(0x1F000000),
                        ambientColor = Color(0x1F000000)
                    )
                    .width(240.dp)
                    .height(55.dp)
                    .background(color = Color(0xFF5E60CE), shape = RoundedCornerShape(size = 4.dp))
                    .padding(start = 22.dp, top = 8.dp, end = 22.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "FINISH",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.46.sp)
            }
        }
        BottomBranding()
    }
}

@Composable
private fun Questionnaire(
    viewModel: YourGoalsViewModel
) {
    Text(
        text = "YOUR GOALS",
        fontSize = 26.sp,
        fontFamily = FontFamily(Font(R.font.lato_regular)),
        fontWeight = FontWeight(700),
        fontStyle = FontStyle.Italic,
        color = Color(0xFF5E60CE),
        letterSpacing = 2.4.sp,
        modifier = Modifier
            .offset(x = 30.dp, y = 35.dp)
            .width(200.dp)
            .height(29.dp)
    )
    Text(
        text = "Help us customize your workout experience by providing us with some details about your workout goals.",
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.lato_light)),
        fontWeight = FontWeight(300),
        modifier = Modifier
            .offset(x = 30.dp, y = 60.dp)
            .width(300.dp)
            .height(65.dp)
    )
    Row {
        Checkbox(
            checked = viewModel.isGainMuscleChecked,
            onCheckedChange = { viewModel.updateGainMuscleChecked(it) },
            modifier = Modifier
                .offset(x = 35.dp, y = 80.dp)
        )
        Text(
            text = "Gain muscle",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            modifier = Modifier
                .offset(x = 35.dp, y = 93.dp)
        )
    }
    Row {
        Checkbox(
            checked = viewModel.isImproveEnduranceChecked,
            onCheckedChange = { viewModel.updateImproveEnduranceChecked(it) },
            modifier = Modifier
                .offset(x = 35.dp, y = 80.dp)
        )
        Text(
            text = "Improve endurance",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            modifier = Modifier
                .offset(x = 35.dp, y = 93.dp)
        )
    }
    Row {
        Checkbox(
            checked = viewModel.isLoseWeightChecked,
            onCheckedChange = { viewModel.updateLoseWeightChecked(it) },
            modifier = Modifier
                .offset(x = 35.dp, y = 80.dp)
        )
        Text(
            text = "Lose weight",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            modifier = Modifier
                .offset(x = 35.dp, y = 93.dp)
        )
    }
    Row {
        Checkbox(
            checked = viewModel.isIncreaseFlexibilityChecked,
            onCheckedChange = { viewModel.updateIncreaseFlexibilityChecked(it) },
            modifier = Modifier
                .offset(x = 35.dp, y = 80.dp)
        )
        Text(
            text = "Increase flexibility",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            modifier = Modifier
                .offset(x = 35.dp, y = 93.dp)
        )
    }
    Row {
        Checkbox(
            checked = viewModel.isExerciseRegularlyChecked,
            onCheckedChange = { viewModel.updateExerciseRegularlyChecked(it) },
            modifier = Modifier
                .offset(x = 35.dp, y = 80.dp)
        )
        Text(
            text = "Exercise regularly",
            fontFamily = FontFamily(Font(R.font.lato_regular)),
            modifier = Modifier
                .offset(x = 35.dp, y = 93.dp)
        )
    }
}

@Preview(name = "Your goals light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RoutinesScreenPreview() {
    MusicTonedTheme {
        YourGoalsScreen(
            onClickFinish = {},
            viewModel = YourGoalsViewModel()
        )
    }
}