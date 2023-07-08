package com.example.musictoned.player

import android.os.CountDownTimer
import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musictoned.R
import com.example.musictoned.util.LocalStorage
import com.example.musictoned.util.TimerUtils.formatTime
import com.example.musictoned.workoutcreation.AllWorkouts.getWorkout
import com.example.musictoned.workoutcreation.Exercise
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise

/**
 * Influenced by composable timer example and sample Compose app
 * Ref: https://medium.com/geekculture/exploring-jetpack-compose-build-a-simple-countdown-timer-app-3151f8000529
 * Ref: https://github.com/android/compose-samples/blob/main/Jetsurvey/app/src/main/java/com/example/compose/jetsurvey/survey/SurveyViewModel.kt
 */

class PlayerViewModelFactory(
    private val routineID: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            return PlayerViewModel(routineID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PlayerViewModel(
    routineID: Int,
    workout: Workout? = null
) : ViewModel() {
    // Used to detect when we're just running a preview
    private val isPreview: Boolean = workout != null

    // Accept the provided workout for the preview, otherwise loads from data store
    private val routine = workout ?: getWorkout(routineID)

    private var countDownTimer: CountDownTimer? = null

    // TODO - Ensure we can't create routines with < 1 exercise
    private var exerciseIndex = 1

    private val _playerScreenData = mutableStateOf(createPlayerScreenData())
    val playerScreenData: PlayerScreenData
        get() = _playerScreenData.value

    private val _time = MutableLiveData(playerScreenData.exerciseTimeMillis.formatTime())
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
        handleTimerValues(false, playerScreenData.exerciseTimeMillis.formatTime())
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(playerScreenData.exerciseTimeMillis, 1000) {

            override fun onTick(millisRemaining: Long) {
                handleTimerValues(true, millisRemaining.formatTime())
            }

            // TODO - Need to transition between workouts here
            override fun onFinish() {
                pauseTimer()
            }
        }.start()
    }

    // TODO - Connect to skip button
    fun onSkipPressed() {
        changeExercise(exerciseIndex + 1)
    }

    private fun changeExercise(newExerciseIndex: Int) {
        exerciseIndex = newExerciseIndex
        _playerScreenData.value = createPlayerScreenData()
        // TODO - Reset timer
    }

    private fun createPlayerScreenData(): PlayerScreenData {
        val workoutExercise: WorkoutExercise = routine.exercises[exerciseIndex - 1]
        val exercise: Exercise = workoutExercise.getExercise()

        return PlayerScreenData(
            routineName = routine.name,
            exerciseIndex = exerciseIndex,
            exerciseCount = routine.exercises.size,
            exerciseImageId = if (this.isPreview) R.drawable.side_to_side_reaches else LocalStorage.getExerciseImage(exercise.imageName),
            exerciseName = exercise.name,
            songName = workoutExercise.getSong(),
            exerciseTimeMillis = workoutExercise.getLength() * 1000
        )
    }
}

data class PlayerScreenData(
    val routineName: String,
    val exerciseIndex: Int,
    val exerciseCount: Int,
    @DrawableRes val exerciseImageId: Int,
    val exerciseName: String,
    val songName: String,
    val exerciseTimeMillis: Long
)
