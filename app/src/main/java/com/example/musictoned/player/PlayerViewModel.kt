package com.example.musictoned.player

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musictoned.util.TimerUtils
import com.example.musictoned.util.TimerUtils.formatTime

/**
 * Influenced by composable timer example
 * Ref: https://medium.com/geekculture/exploring-jetpack-compose-build-a-simple-countdown-timer-app-3151f8000529
 */

class PlayerViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            return PlayerViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PlayerViewModel : ViewModel() {
    private var countDownTimer: CountDownTimer? = null

    private val _time = MutableLiveData(TimerUtils.TIME_COUNTDOWN.formatTime())
    val time: LiveData<String> = _time

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private fun handleTimerValues(isPlaying: Boolean, text: String) {
        _isPlaying.value = isPlaying
        _time.value = text
    }

    fun handleCountDownTimer() {
        println("Changing isPlaying to" + !isPlaying.value!!)
        if (isPlaying.value == true) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        handleTimerValues(false, TimerUtils.TIME_COUNTDOWN.formatTime())
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(TimerUtils.TIME_COUNTDOWN, 1000) {

            override fun onTick(millisRemaining: Long) {
                handleTimerValues(true, millisRemaining.formatTime())
            }

            // TODO - Need to transition between workouts here
            override fun onFinish() {
                pauseTimer()
            }
        }.start()
    }
}