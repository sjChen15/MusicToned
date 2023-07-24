package com.example.musictoned.workoutcreation

import java.util.LinkedList

data class Workout (
    var name: String,
    val exercises: LinkedList<WorkoutExercise> = LinkedList(),
    var totalDurationSeconds: Long = 0,
    var totalCalories: Float = 0F ){

    fun saveExercises(newExercises: List<WorkoutExercise>) {
        exercises.clear()
        totalDurationSeconds = 0
        totalCalories = 0F

        for(exercise:WorkoutExercise in newExercises){
            exercises.add(exercise)
            totalDurationSeconds += exercise.getLength()
            totalCalories += exercise.getTotalCalories()
        }
    }

    fun getWorkoutDurationHourFormat(): String {
        var seconds: Long = 0
        val totalTime: String

        for(ex in exercises){
            seconds += ex.getLength()
        }

        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        seconds %= 60

        var hourPrefix = "0"
        var minutePrefix = "0"
        var secondPrefix = "0"

        if (hours >= 10) {
            hourPrefix = ""
        }
        if (minutes >= 10) {
            minutePrefix = ""
        }
        if (seconds >= 10) {
            secondPrefix = ""
        }

        return "$hourPrefix$hours:$minutePrefix$minutes:$secondPrefix$seconds"
    }
}