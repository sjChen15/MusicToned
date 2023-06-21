package com.example.musictoned.spotifyAuthorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeViewModel.kt
 */

class SpotifyAuthorizationViewModel() : ViewModel() {
}

class SpotifyAuthorizationViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpotifyAuthorizationViewModel::class.java)) {
            return SpotifyAuthorizationViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}