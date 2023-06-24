package com.example.musictoned.workoutcreation

data class Workout (private val id: Int, val exercises: List<WorkoutExercise> = listOf()){
    override fun toString(): String = "Category [id: ${id}, exercises: ${this.exercises}]"
}