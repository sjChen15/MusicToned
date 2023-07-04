package com.example.musictoned

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musictoned.Destinations.ABOUT_YOU_ROUTE
import com.example.musictoned.Destinations.ROUTINES_ROUTE
import com.example.musictoned.Destinations.ROUTINE_ROUTE
import com.example.musictoned.Destinations.WELCOME_ROUTE
import com.example.musictoned.Destinations.EDIT_ROUTINE_ROUTE
import com.example.musictoned.Destinations.ADD_EXERCISE_ROUTE
import com.example.musictoned.Destinations.ANALYTICS_ROUTE
import com.example.musictoned.Destinations.PLAYER_ROUTE
import com.example.musictoned.Destinations.SETTINGS_ROUTE
import com.example.musictoned.addExercise.AddExerciseRoute
import com.example.musictoned.editRoutine.EditRoutineRoute
import com.example.musictoned.Destinations.YOUR_GOALS_ROUTE
import com.example.musictoned.aboutYou.AboutYouRoute
import com.example.musictoned.analytics.AnalyticsRoute
import com.example.musictoned.player.PlayerRoute
import com.example.musictoned.routine.RoutineRoute
import com.example.musictoned.routines.RoutinesRoute
import com.example.musictoned.settings.SettingsRoute
import com.example.musictoned.welcome.WelcomeRoute
import com.example.musictoned.yourGoals.YourGoalsRoute

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/Navigation.kt
 */

/**
 * Navigation destinations
 */
object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val ABOUT_YOU_ROUTE = "aboutYou"
    const val YOUR_GOALS_ROUTE = "yourGoals"
    const val ROUTINES_ROUTE = "routines"
    const val ROUTINE_ROUTE = "routine"
    const val EDIT_ROUTINE_ROUTE = "editRoutine"
    const val ADD_EXERCISE_ROUTE = "addExercise"
    const val ANALYTICS_ROUTE = "analytics"
    const val SETTINGS_ROUTE = "settings"
    const val PLAYER_ROUTE = "player"
}

@Composable
fun MusicTonedNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onClickCreateAnAccount = {
                    navController.navigate(ABOUT_YOU_ROUTE)
                }
            )
        }

        composable(ABOUT_YOU_ROUTE) {
            AboutYouRoute(
                onClickContinue = {
                    navController.navigate(YOUR_GOALS_ROUTE)
                }
            )
        }

        composable(YOUR_GOALS_ROUTE) {
            YourGoalsRoute(
                onClickFinish = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }

        composable(ROUTINES_ROUTE) {
            RoutinesRoute(
                onNavigateToRoutine = {
                    navController.navigate("$ROUTINE_ROUTE/$it")
                },
                onNavigateToEditRoutine = {
                    navController.navigate(EDIT_ROUTINE_ROUTE)
                },
                onNavigateToRoutines = {
                    /* NOOP since we don't need to route to ourselves */
                },
                onNavigateToAnalytics = {
                    navController.navigate(ANALYTICS_ROUTE)
                },
                onNavigateToSettings = {
                    navController.navigate(SETTINGS_ROUTE)
                }
            )
        }

        composable(ANALYTICS_ROUTE) {
            AnalyticsRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                },
                onNavigateToAnalytics = {
                    /* NOOP since we don't need to route to ourselves */
                },
                onNavigateToSettings = {
                    navController.navigate(SETTINGS_ROUTE)
                }
            )
        }

        composable(SETTINGS_ROUTE) {
            SettingsRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                },
                onNavigateToAnalytics = {
                    navController.navigate(ANALYTICS_ROUTE)
                },
                onNavigateToSettings = {
                    /* NOOP since we don't need to route to ourselves */
                }
            )
        }

        composable("$ROUTINE_ROUTE/{routineID}") {
            val routineID = it.arguments?.getString("routineID")

            RoutineRoute(
                onNavigateToEditRoutine = {
                    navController.navigate(EDIT_ROUTINE_ROUTE)
                },
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                },
                onNavigateToPlayer = {
                    navController.navigate("$PLAYER_ROUTE/{routineID}")
                },
                routineID = routineID?.toInt()
            )
        }

        composable("$PLAYER_ROUTE/{routineID}") {
            PlayerRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }

        composable(EDIT_ROUTINE_ROUTE) {
            EditRoutineRoute(
                onNavigateToRoutine = {
                    navController.navigate("$ROUTINE_ROUTE/$it")
                },
                onNavigateToAddExercise = {
                    navController.navigate(ADD_EXERCISE_ROUTE)
                }
            )
        }

        composable(ADD_EXERCISE_ROUTE) {
            AddExerciseRoute(
                onNavigateToEditRoutine = {
                    navController.navigate(EDIT_ROUTINE_ROUTE)
                }
            )
        }
    }
}