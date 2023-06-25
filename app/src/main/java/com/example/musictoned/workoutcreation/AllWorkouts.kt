package com.example.musictoned.workoutcreation

import com.example.musictoned.util.LocalStorage
object AllWorkouts {
    private var allWorkouts: ArrayList<Workout> = LocalStorage.getWorkouts()

    fun getAllWorkouts(): ArrayList<Workout> = allWorkouts

    fun addWorkout(workout: Workout){
        allWorkouts.add(workout)
    }

    fun getNumberOfWorkouts(): Int = allWorkouts.size
}