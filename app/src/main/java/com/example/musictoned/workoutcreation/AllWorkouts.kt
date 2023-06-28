package com.example.musictoned.workoutcreation

import com.example.musictoned.util.LocalStorage
import java.util.LinkedList

object AllWorkouts {
    //local storage
    //private var allWorkouts: ArrayList<Workout> = LocalStorage.getWorkouts()

    //instantiate allWorkouts with one default workout
    private var allWorkouts: ArrayList<Workout> = arrayListOf(Workout("I love squats"))
    private var workoutInProgress: Workout = Workout("New Workout")

    fun setWorkoutInProgress(workout: Workout){
        workoutInProgress = workout
    }

    fun getWorkoutInProgress(): Workout{
        return workoutInProgress
    }

    fun getAllWorkouts(): ArrayList<Workout> = allWorkouts

    fun addWorkout(workout: Workout){
        allWorkouts.add(workout)
    }

    fun saveInProgress(){
        allWorkouts.add(workoutInProgress)
    }
    fun getNumberOfWorkouts(): Int = allWorkouts.size
}