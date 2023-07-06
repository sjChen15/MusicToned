package com.example.musictoned.util

import java.util.concurrent.TimeUnit

/**
 * Influenced by composable timer example
 * Ref: https://medium.com/geekculture/exploring-jetpack-compose-build-a-simple-countdown-timer-app-3151f8000529
 */

object TimerUtils {
    const val TIME_COUNTDOWN: Long = 60000L
    private const val TIME_FORMAT = "%02d:%02d"

    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}