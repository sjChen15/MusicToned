package com.example.musictoned;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musictoned.workoutcreation.Workout

class WorkoutViewModel: ViewModel() {

    private val _workout = MutableLiveData<Workout>()
    val workout: LiveData<Workout> = _workout

    fun setWorkout( Workout: Workout ){
        _workout.value = Workout
    }

    fun getWorkout(): Workout? {
        return _workout.value
    }
}
