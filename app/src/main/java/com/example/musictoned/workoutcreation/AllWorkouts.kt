package com.example.musictoned.workoutcreation

import com.example.musictoned.util.LocalStorage
object AllWorkouts {
    private lateinit var allWorkouts: ArrayList<Workout>
    init {
        var allWorkouts = LocalStorage.getWorkouts()
    }

    fun getAllWorkouts(): ArrayList<Workout> = allWorkouts

    fun addWorkout(workout: Workout){
        allWorkouts.add(workout)
    }

    fun getNumberOfWorkouts(): Int = allWorkouts.size
}