package com.example.musictoned.workoutcreation

import com.example.musictoned.util.LocalStorage

object AllWorkouts {
    //local storage
    private var allWorkouts: ArrayList<Workout> = LocalStorage.getWorkouts()

    //instantiate allWorkouts with one default workout
//    private var allWorkouts: ArrayList<Workout> = arrayListOf(Workout("I love squats"))
    private var workoutInProgress: Workout = Workout("New Workout")

    private fun setWorkoutInProgress(workout: Workout){
        workoutInProgress = workout
        //LocalStorage.writeWorkouts(arrayListOf())

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
        LocalStorage.reWriteWorkoutsFile()
    }
    fun addWorkout(workout: Workout){
        allWorkouts.add(workout)
    }

    //this function should only rewrite the updated workout to local storage, not add a new workout, right?
    fun saveWorkout(workout: Workout){

        //check if workout is already in the arrayList of workouts
        if (workout == getWorkout(workout.hashCode())){
            //just rewrite file to local storage with updated data as
            storeAllWorkouts()
        } else {
            setWorkoutInProgress(workout)
            allWorkouts.add(workoutInProgress)
            workoutInProgress = Workout("New Workout")
            storeAllWorkouts()
        }

    }
    fun getNumberOfWorkouts(): Int = allWorkouts.size

    private fun storeAllWorkouts(){
        LocalStorage.writeWorkouts(allWorkouts)
    }
}