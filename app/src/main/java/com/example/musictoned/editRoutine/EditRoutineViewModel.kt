package com.example.musictoned.editRoutine

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.AllWorkouts.saveWorkout
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise

class EditRoutineViewModelFactory(
    private val routineID: Int?
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRoutineViewModel::class.java)) {
            return EditRoutineViewModel(routineID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class EditRoutineViewModel(
    routineID: Int?,
    _workout: Workout? = null
) : ViewModel() {
    // Accept the provided workout for the preview, otherwise loads from data store
    val workout =
        // Preview
        _workout
            ?: if (routineID == null) {
                // New workout
                AllWorkouts.getWorkoutInProgress()
            } else {
                // Existing workout
                AllWorkouts.getWorkout(routineID)
            }

    private val _exercises = workout.exercises.toMutableStateList()
    val exercises: List<WorkoutExercise>
        get() = _exercises

    fun remove(exercise: WorkoutExercise) {
        _exercises.remove(exercise)
    }

    fun add(exercise: WorkoutExercise) {
        _exercises.add(exercise)
    }

    fun moveUp(exercise: WorkoutExercise) {
        val index = _exercises.indexOf(exercise)

        // Only move up if we're not already at the top
        if (index != 0) {
            _exercises.remove(exercise)
            _exercises.add(index - 1, exercise)
        }
    }

    fun moveDown(exercise: WorkoutExercise) {
        val index = _exercises.indexOf(exercise)

        // Only move down if we're not already at the bottom
        if (index != _exercises.size - 1) {
            _exercises.remove(exercise)
            _exercises.add(index + 1, exercise)
        }
    }

    fun save() {
        // Attach the songs to the workout

        //Should we just make it so that exercises have a default song?
        for(ex in _exercises){
            if (ex.getSong() == ""){
                //ex.setSongByBPM(ex.getBpmMode())
                //TODO - (DisableSpotifySongs) if you do not need to work with spotify, replace the above line with the below line:
                ex.setSong("Despacito", "")
            }
        }

        // Replace the exercises in the workout with the exercise from the state
        workout.saveExercises(_exercises)

        // Persist the workout to local storage
        saveWorkout(workout)
    }
}