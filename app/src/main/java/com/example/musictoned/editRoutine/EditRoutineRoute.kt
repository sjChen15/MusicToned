package com.example.musictoned.editRoutine

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeRoute.kt
 */

@Composable
fun EditRoutineRoute(
    onNavigateToRoutine: (routineID: Int?) -> Unit,
    routineID: Int?
) {
    val editRoutineViewModel: EditRoutineViewModel = viewModel(factory = EditRoutineViewModelFactory(routineID))

    EditRoutineScreen(
        viewModel = editRoutineViewModel,
        onNavigateToRoutine = onNavigateToRoutine
    )
}