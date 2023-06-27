package com.example.musictoned

import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musictoned.Destinations.ABOUT_YOU_ROUTE
import com.example.musictoned.Destinations.ROUTINES_ROUTE
import com.example.musictoned.Destinations.ROUTINE_ROUTE
import com.example.musictoned.Destinations.SPOTIFY_AUTHORIZATION_ROUTE
import com.example.musictoned.Destinations.SPOTIFY_BETA_ROUTE
import com.example.musictoned.Destinations.WELCOME_ROUTE
import com.example.musictoned.Destinations.EDIT_ROUTINE_ROUTE
import com.example.musictoned.Destinations.ADD_EXERCISE_ROUTE
import com.example.musictoned.addExercise.AddExerciseRoute
import com.example.musictoned.editRoutine.EditRoutineRoute
import com.example.musictoned.Destinations.YOUR_GOALS_ROUTE
import com.example.musictoned.aboutYou.AboutYouRoute
import com.example.musictoned.routine.RoutineRoute
import com.example.musictoned.routines.RoutinesRoute
import com.example.musictoned.spotifyAuthorization.SpotifyAuthorizationRoute
import com.example.musictoned.spotifyAuthorization.SpotifyBetaRoute
import com.example.musictoned.welcome.WelcomeRoute
import com.example.musictoned.workoutcreation.Workout
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
    const val SPOTIFY_AUTHORIZATION_ROUTE = "spotifyAuthorization"
    const val SPOTIFY_BETA_ROUTE = "spotifyBeta" // TODO - Remove, just for Harsh's testing
    const val ROUTINES_ROUTE = "routines"
    const val ROUTINE_ROUTE = "routine"
    const val EDIT_ROUTINE_ROUTE = "editRoutine"
    const val ADD_EXERCISE_ROUTE = "addExercise"
}

@Composable
fun MusicTonedNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = ROUTINES_ROUTE
        //startDestination = WELCOME_ROUTE
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
                    navController.navigate(SPOTIFY_AUTHORIZATION_ROUTE)
                }
            )
        }

        composable(SPOTIFY_AUTHORIZATION_ROUTE) {
            SpotifyAuthorizationRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }

        composable(ROUTINES_ROUTE) {
            RoutinesRoute(
                onNavigateToRoutine = {
                    navController.navigate("$ROUTINE_ROUTE/$it")
                },
                onNavigateToSpotifyBeta = {
                    navController.navigate(SPOTIFY_BETA_ROUTE)
                },
                onNavigateToEditRoutine = {
                    navController.navigate(EDIT_ROUTINE_ROUTE)
                }
            )
        }

        composable(SPOTIFY_BETA_ROUTE) {
            SpotifyBetaRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }

        composable(
            "$ROUTINE_ROUTE/{routineID}"
        ) {
            val routineID = it.arguments?.getString("routineID")

            RoutineRoute(
                onNavigateToEditRoutine = {
                    navController.navigate(EDIT_ROUTINE_ROUTE)
                },
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                },
                routineID = routineID?.toInt()
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