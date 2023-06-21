package com.example.musictoned

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musictoned.Destinations.ROUTINES_ROUTE
import com.example.musictoned.Destinations.ROUTINE_ROUTE
import com.example.musictoned.Destinations.WELCOME_ROUTE
import com.example.musictoned.routine.RoutineRoute
import com.example.musictoned.routines.RoutinesRoute
import com.example.musictoned.welcome.WelcomeRoute

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/Navigation.kt
 */

/**
 * Navigation destinations
 */
object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val ROUTINES_ROUTE = "routines"
    const val ROUTINE_ROUTE = "routine"
}

@Composable
fun MusicTonedNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }

        composable(ROUTINES_ROUTE) {
            RoutinesRoute(
                onNavigateToRoutine = {
                    navController.navigate(ROUTINE_ROUTE)
                }
            )
        }

        composable(ROUTINE_ROUTE) {
            RoutineRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }
    }
}