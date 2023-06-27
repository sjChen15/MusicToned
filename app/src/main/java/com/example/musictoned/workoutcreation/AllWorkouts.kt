package com.example.musictoned.workoutcreation

import com.example.musictoned.util.LocalStorage
import java.util.LinkedList

object AllWorkouts {
    //local storage
    //private var allWorkouts: ArrayList<Workout> = LocalStorage.getWorkouts()

    //instantiate allWorkouts with one default workout
    private var allWorkouts: ArrayList<Workout> = arrayListOf(Workout("I love squats"))

    fun getAllWorkouts(): ArrayList<Workout> = allWorkouts

    fun addWorkout(workout: Workout){
        allWorkouts.add(workout)
    }

    fun getNumberOfWorkouts(): Int = allWorkouts.size
}