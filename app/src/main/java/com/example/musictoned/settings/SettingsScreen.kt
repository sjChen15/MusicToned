package com.example.musictoned.settings

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.profile.Profile
import com.example.musictoned.profile.Profile.profile
import com.example.musictoned.ui.theme.FontName
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen
import com.example.musictoned.util.BottomBar
import com.example.musictoned.util.BottomNavPages
import com.example.musictoned.util.TextFieldRegex

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeScreen.kt
 */

@Composable
fun SettingsScreen(
    onNavigateToRoutines: (charOffset: Int) -> Unit,
    onNavigateToAnalytics: (charOffset: Int) -> Unit,
    onNavigateToSettings: (charOffset: Int) -> Unit,
    viewModel: SettingsViewModel
) {

    Surface(
        modifier = Modifier
            .supportWideScreen()
            .fillMaxHeight()
            .fillMaxWidth()) {
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
                    currentPage = BottomNavPages.SETTINGS
                )
            },
            content = { innerPadding ->
                LazyColumn ( modifier = Modifier
                    .padding(innerPadding)
                    .height(800.dp))
                {
                    item{
                        OutlinedTextField(
                            value = viewModel.name,
                            onValueChange = { name ->
                                if (name.isEmpty() || name.matches(TextFieldRegex.textOnlyRegex)) {
                                    viewModel.updateName(name)
                                    profile.name = name
                                }
                            },
                            label = { Text("Name") },
                            maxLines = 1,
                            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                            modifier = Modifier
                                .offset(x = 31.dp, y = 20.dp)
                                .width(320.dp)
                        )
                    }
                    item{
                        OutlinedTextField(
                            value = viewModel.age,
                            onValueChange = { age ->
                                if (age.isEmpty() || age.matches(TextFieldRegex.wholeNumberRegex)) {
                                    viewModel.updateAge(age)
                                    profile.age = if (age.isEmpty()) 0 else age.toInt()
                                }
                            },
                            label = { Text("Age") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                            modifier = Modifier
                                .offset(x = 31.dp, y = 20.dp)
                                .width(320.dp)
                        )
                    }
                    item{
                        Row {
                            OutlinedTextField(
                                value = viewModel.weight,
                                onValueChange = { weight ->
                                    if (weight.isEmpty() || weight.matches(TextFieldRegex.decimalNumberRegex)) {
                                        viewModel.updateWeight(weight)
                                        profile.weight =
                                            if (weight.isEmpty()) 0F else weight.toFloat()
                                    }
                                },
                                label = { Text("Weight") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                                modifier = Modifier
                                    .offset(x = 31.dp, y = 20.dp)
                                    .width(200.dp)
                            )
                            ExposedDropdownMenuBox(
                                expanded = viewModel.isWeightUnitMenuExpanded,
                                onExpandedChange = { viewModel.updateWeightUnitMenuExpanded(!viewModel.isWeightUnitMenuExpanded) },
                                modifier = Modifier
                                    .offset(x = 45.dp, y = 27.dp)
                                    .width(105.dp)
                            ) {
                                TextField(
                                    readOnly = true,
                                    value = viewModel.weightUnit.toString(),
                                    onValueChange = { },
                                    label = { Text("Unit") },
                                    textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.isWeightUnitMenuExpanded)
                                    },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier.menuAnchor()
                                )
                                ExposedDropdownMenu(
                                    expanded = viewModel.isWeightUnitMenuExpanded,
                                    onDismissRequest = {
                                        viewModel.updateWeightUnitMenuExpanded(false)
                                    }
                                ) {
                                    viewModel.weightUnits.forEach { weightUnitOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                viewModel.updateWeightUnit(weightUnitOption)
                                                viewModel.updateWeightUnitMenuExpanded(false)
                                                profile.weightUnit = weightUnitOption
                                            },
                                            text = {
                                                Text(text = weightUnitOption.toString())
                                            }
                                        )
                                    }

                                }
                            }
                        }
                    }
                    item{
                        Row {
                            OutlinedTextField(
                                value = viewModel.height,
                                onValueChange = { height ->
                                    if (height.isEmpty() || height.matches(TextFieldRegex.decimalNumberRegex)) {
                                        viewModel.updateHeight(height)
                                        profile.height =
                                            if (height.isEmpty()) 0F else height.toFloat()
                                    }
                                },
                                label = { Text("Height") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                                modifier = Modifier
                                    .offset(x = 31.dp, y = 20.dp)
                                    .width(200.dp)
                            )
                            ExposedDropdownMenuBox(
                                expanded = viewModel.isHeightUnitMenuExpanded,
                                onExpandedChange = { viewModel.updateHeightUnitMenuExpanded(!viewModel.isHeightUnitMenuExpanded) },
                                modifier = Modifier
                                    .offset(x = 45.dp, y = 27.dp)
                                    .width(105.dp)
                            ) {
                                TextField(
                                    readOnly = true,
                                    value = viewModel.heightUnit.toString(),
                                    onValueChange = { },
                                    label = { Text("Unit") },
                                    textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.isHeightUnitMenuExpanded)
                                    },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier.menuAnchor()
                                )
                                ExposedDropdownMenu(
                                    expanded = viewModel.isHeightUnitMenuExpanded,
                                    onDismissRequest = {
                                        viewModel.updateHeightUnitMenuExpanded(false)
                                    }
                                ) {
                                    viewModel.heightUnits.forEach { heightUnitOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                viewModel.updateHeightUnit(heightUnitOption)
                                                viewModel.updateHeightUnitMenuExpanded(false)
                                                profile.heightUnit = heightUnitOption
                                            },
                                            text = {
                                                Text(text = heightUnitOption.toString())
                                            }
                                        )
                                    }

                                }
                            }
                        }
                    }
                    item{
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = viewModel.calorieGoal,
                                onValueChange = { goal ->
                                    if (goal.isEmpty() || goal.matches(TextFieldRegex.wholeNumberRegex)) {
                                        viewModel.updateCalorieGoal(goal)
                                        profile.calorieGoal =
                                            if (goal.isEmpty()) 0 else goal.toInt()
                                    }
                                },
                                label = { Text("Calorie Goal Per Day") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
                                modifier = Modifier
                                    .offset(x = 31.dp, y = 20.dp)
                                    .width(280.dp)
                            )
                            Text(
                                text = "kCal",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(500),
                                    fontStyle = FontStyle.Normal,
                                    color = Color(0xFF484848),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 33.dp)
                                    .padding(all = 3.dp)
                            )
                        }
                    }
                    item{
                        Row {
                            Checkbox(
                                checked = viewModel.isGainMuscleChecked,
                                onCheckedChange = {
                                    viewModel.updateGainMuscleChecked(it)
                                    profile.gainMuscle = it
                                },
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 20.dp)
                            )
                            Text(
                                text = "Gain muscle",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(500),
                                    fontStyle = FontStyle.Normal,
                                    color = Color(0xFF484848),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 33.dp)
                            )
                        }
                    }
                    item{
                        Row {
                            Checkbox(
                                checked = viewModel.isImproveEnduranceChecked,
                                onCheckedChange = {
                                    viewModel.updateImproveEnduranceChecked(it)
                                    profile.improveEndurance = it
                                },
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 20.dp)
                            )
                            Text(
                                text = "Improve endurance",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(500),
                                    fontStyle = FontStyle.Normal,
                                    color = Color(0xFF484848),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 33.dp)
                            )
                        }
                    }
                    item{
                        Row {
                            Checkbox(
                                checked = viewModel.isLoseWeightChecked,
                                onCheckedChange = {
                                    viewModel.updateLoseWeightChecked(it)
                                    profile.loseWeight = it
                                },
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 20.dp)
                            )
                            Text(
                                text = "Lose weight",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(500),
                                    fontStyle = FontStyle.Normal,
                                    color = Color(0xFF484848),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 33.dp)
                            )
                        }
                    }
                    item{
                        Row {
                            Checkbox(
                                checked = viewModel.isIncreaseFlexibilityChecked,
                                onCheckedChange = {
                                    viewModel.updateIncreaseFlexibilityChecked(it)
                                    profile.increaseFlexibility = it
                                },
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 20.dp)
                            )
                            Text(
                                text = "Increase flexibility",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(500),
                                    fontStyle = FontStyle.Normal,
                                    color = Color(0xFF484848),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 33.dp)
                            )
                        }
                    }
                    item{
                        Row {
                            Checkbox(
                                checked = viewModel.isExerciseRegularlyChecked,
                                onCheckedChange = {
                                    viewModel.updateExerciseRegularlyChecked(it)
                                    profile.exerciseRegularly = it
                                },
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 20.dp)
                            )
                            Text(
                                text = "Exercise regularly",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(500),
                                    fontStyle = FontStyle.Normal,
                                    color = Color(0xFF484848),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier
                                    .offset(x = 35.dp, y = 33.dp)
                            )
                        }
                    }
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
            text = "SETTINGS",
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

@Preview(name = "Settings light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SettingsScreenPreview() {
    MusicTonedTheme {
        SettingsScreen(
            onNavigateToRoutines = {},
            onNavigateToAnalytics = {},
            onNavigateToSettings = {},
            viewModel = SettingsViewModel()
        )
    }
}