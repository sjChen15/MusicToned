package com.example.musictoned.aboutYou

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.example.musictoned.profile.Profile
import com.example.musictoned.profile.ProfileClass
import com.example.musictoned.util.LocalStorage
import com.example.musictoned.workoutcreation.AllWorkouts

@Composable
fun AboutYouScreen(
    onClickContinue: () -> Unit,
    viewModel: AboutYouViewModel
) {
    Surface(modifier = Modifier
        .supportWideScreen()
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            Questionnaire(
                viewModel = viewModel
            )
            Button(
                onClick = {
                    LocalStorage.writeProfile(Profile.profile)
                    onClickContinue()
                },
                shape = RoundedCornerShape(size = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .offset(x = 78.dp, y = 140.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF5E60CE),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .width(236.dp)
                    .height(55.dp)
                    .padding(start = 22.dp, top = 8.dp, end = 22.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF5E60CE),
                    letterSpacing = 0.46.sp)
            }
        }
        BottomBranding()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Questionnaire(
    viewModel: AboutYouViewModel
) {
    val wholeNumberRegex = remember { Regex("^\\d+\$") }
    val decimalNumberRegex = remember{ Regex("^\\d*\\.?\\d*\$") }
    val textOnlyRegex = remember{ Regex("^[a-zA-Z]*\$") }

    val profile: ProfileClass = Profile.profile //current profile
    Text(
        text = "ABOUT YOU",
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
        text = "Welcome to MusicToned! Let’s get to know a bit more about you.",
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.lato_light)),
        fontWeight = FontWeight(300),
        modifier = Modifier
            .offset(x = 30.dp, y = 60.dp)
            .width(300.dp)
            .height(48.dp)
    )
    OutlinedTextField(
        value = viewModel.name,
        onValueChange = { name ->
                if(name.isEmpty() || name.matches(textOnlyRegex)){
                    viewModel.updateName(name)
                    profile.name = name
                }
        },
        label = { Text("Name*") },
        maxLines = 1,
        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
        modifier = Modifier
            .offset(x = 31.dp, y = 80.dp)
            .width(320.dp)
    )
    OutlinedTextField(
        value = viewModel.age,
        onValueChange = { age ->
            if(age.isEmpty() || age.matches(wholeNumberRegex)) {
                viewModel.updateAge(age)
                profile.age = if(age.isEmpty()) 0 else age.toInt()
            }
        },
        label = { Text("Age") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
        modifier = Modifier
            .offset(x = 31.dp, y = 80.dp)
            .width(320.dp)
    )
    Row {
        OutlinedTextField(
            value = viewModel.weight,
            onValueChange = { weight ->
                if(weight.isEmpty() || weight.matches(decimalNumberRegex)) {
                    viewModel.updateWeight(weight)
                    profile.weight = if(weight.isEmpty()) 0F else weight.toFloat()
                }
            },
            label = { Text("Weight") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
            modifier = Modifier
                .offset(x = 31.dp, y = 80.dp)
                .width(200.dp)
        )
        ExposedDropdownMenuBox(
            expanded = viewModel.isWeightUnitMenuExpanded,
            onExpandedChange = { viewModel.updateWeightUnitMenuExpanded(!viewModel.isWeightUnitMenuExpanded) },
            modifier = Modifier
                .offset(x = 45.dp, y = 87.dp)
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
    Row {
        OutlinedTextField(
            value = viewModel.height,
            onValueChange = { height ->
                if(height.isEmpty() || height.matches(decimalNumberRegex)) {
                    viewModel.updateHeight(height)
                    profile.height = if(height.isEmpty()) 0F else height.toFloat()
                }
            },
            label = { Text("Height") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.lato_light))),
            modifier = Modifier
                .offset(x = 31.dp, y = 80.dp)
                .width(200.dp)
        )
        ExposedDropdownMenuBox(
            expanded = viewModel.isHeightUnitMenuExpanded,
            onExpandedChange = { viewModel.updateHeightUnitMenuExpanded(!viewModel.isHeightUnitMenuExpanded) },
            modifier = Modifier
                .offset(x = 45.dp, y = 87.dp)
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
    Text(
        text = "*NOTE: All personal health info is optional and will only be used for more accurate workout analytics",
        fontSize = 11.sp,
        fontFamily = FontFamily(Font(R.font.lato_regular)),
        fontWeight = FontWeight(500),
        color = Color(0xFF707070),
        lineHeight = 15.sp,
        modifier = Modifier
            .offset(x = 30.dp, y = 90.dp)
            .width(320.dp)
            .height(40.dp)
    )
}

@Preview(name = "About you light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RoutinesScreenPreview() {
    MusicTonedTheme {
        AboutYouScreen(
            onClickContinue = {},
            viewModel = AboutYouViewModel()
        )
    }
}