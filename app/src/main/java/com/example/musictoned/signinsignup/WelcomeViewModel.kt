package com.example.musictoned.signinsignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeViewModel.kt
 */

class WelcomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun handleContinue(
        email: String,
        onNavigateToSignIn: (email: String) -> Unit,
        onNavigateToSignUp: (email: String) -> Unit,
    ) {
        if (userRepository.isKnownUserEmail(email)) {
            onNavigateToSignIn(email)
        } else {
            onNavigateToSignUp(email)
        }
    }
}

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}