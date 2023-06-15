package com.example.musictoned

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musictoned.Destinations.HOME_ROUTE
import com.example.musictoned.Destinations.SIGN_IN_ROUTE
import com.example.musictoned.Destinations.SIGN_UP_ROUTE
import com.example.musictoned.Destinations.WELCOME_ROUTE
import com.example.musictoned.signinsignup.WelcomeRoute

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/Navigation.kt
 */

/**
 * Navigation destinations
 */
object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val SIGN_UP_ROUTE = "signup/{email}"
    const val SIGN_IN_ROUTE = "signin/{email}"
    const val HOME_ROUTE = "home"
}

@Composable
fun MusicTonedNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute(
                onNavigateToSignIn = {
                    navController.navigate("signin/$it")
                },
                onNavigateToSignUp = {
                    navController.navigate("signup/$it")
                }
            )
        }

        /**
         * TODO - Add other routes
         */
        /*
        composable(SIGN_IN_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignInRoute(
                email = startingEmail,
                onSignInSubmitted = {
                    navController.navigate(HOME_ROUTE)
                },
                onNavUp = navController::navigateUp
            )
        }

        composable(SIGN_UP_ROUTE) {
            val startingEmail = it.arguments?.getString("email")
            SignUpRoute(
                email = startingEmail,
                onSignUpSubmitted = {
                    navController.navigate(HOME_ROUTE)
                },
                onNavUp = navController::navigateUp
            )
        }
        */
    }
}