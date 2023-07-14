package com.example.musictoned.workoutcreation

import com.example.musictoned.util.LocalStorage
import java.util.LinkedList

object AllWorkouts {
    //local storage
    private var allWorkouts: ArrayList<Workout> = LocalStorage.getWorkouts()

    //instantiate allWorkouts with one default workout
//    private var allWorkouts: ArrayList<Workout> = arrayListOf(Workout("I love squats"))
    private var workoutInProgress: Workout = Workout("New Workout")

    fun setWorkoutInProgress(workout: Workout){
        workoutInProgress = workout
    }

    fun getWorkoutInProgress(): Workout{
        return workoutInProgress
    }

    fun getAllWorkouts(): ArrayList<Workout> = allWorkouts

    fun getWorkout(id: Int): Workout {
        return allWorkouts.
            stream().
            filter { workout -> workout.hashCode() == id }.
            findFirst().
            orElse(Workout("New Workout"))
    }

    //removes current workout from the ArrayList, 'allWorkouts', and updates workout file in local storage
    fun deleteWorkout(workout: Workout){
        //need to delete from local storage as well
        allWorkouts.remove(workout)
        LocalStorage.deleteWorkoutFile()
    }
    fun addWorkout(workout: Workout){
        allWorkouts.add(workout)
    }

    fun saveWorkout(){
        allWorkouts.add(workoutInProgress)
        workoutInProgress = Workout("New Workout")
        storeAllWorkouts()
    }
    fun getNumberOfWorkouts(): Int = allWorkouts.size

    fun storeAllWorkouts(){
        LocalStorage.writeWorkouts(allWorkouts)
    }
}