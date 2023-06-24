package com.example.musictoned

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musictoned.Destinations.ABOUT_YOU_ROUTE
import com.example.musictoned.Destinations.ROUTINES_ROUTE
import com.example.musictoned.Destinations.ROUTINE_ROUTE
import com.example.musictoned.Destinations.SPOTIFY_AUTHORIZATION_ROUTE
import com.example.musictoned.Destinations.SPOTIFY_BETA_ROUTE
import com.example.musictoned.Destinations.WELCOME_ROUTE
import com.example.musictoned.aboutYou.AboutYouRoute
import com.example.musictoned.routine.RoutineRoute
import com.example.musictoned.routines.RoutinesRoute
import com.example.musictoned.spotifyAuthorization.SpotifyAuthorizationRoute
import com.example.musictoned.spotifyAuthorization.SpotifyBetaRoute
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
    const val ABOUT_YOU_ROUTE = "aboutYou"
    const val SPOTIFY_AUTHORIZATION_ROUTE = "spotifyAuthorization"
    const val SPOTIFY_BETA_ROUTE = "spotifyBeta" // TODO - Remove, just for Harsh's testing
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
                onClickCreateAnAccount = {
                    navController.navigate(ABOUT_YOU_ROUTE)
                }
            )
        }

        composable(ABOUT_YOU_ROUTE) {
            AboutYouRoute(
                onClickContinue = {
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
                    navController.navigate(ROUTINE_ROUTE)
                },
                onNavigateToSpotifyBeta = {
                    navController.navigate(SPOTIFY_BETA_ROUTE)
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

        composable(ROUTINE_ROUTE) {
            RoutineRoute(
                onNavigateToRoutines = {
                    navController.navigate(ROUTINES_ROUTE)
                }
            )
        }
    }
}