package com.example.musictoned.workoutcreation

data class Workout (private val id: Int, val exercises: ArrayList<WorkoutExercise> = arrayListOf<WorkoutExercise>() ){
    fun addExercise(exercise: WorkoutExercise){
        exercises.add(exercise)
    }
}