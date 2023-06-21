package com.example.musictoned.spotifyAuthorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Influenced by composable UI example provided by Android
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/signinsignup/WelcomeViewModel.kt
 */

class SpotifyBetaViewModel() : ViewModel() {
}

class SpotifyBetaViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpotifyBetaViewModel::class.java)) {
            return SpotifyBetaViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}