package com.example.musictoned.workoutcreation

import java.util.LinkedList

data class Workout (
    var name: String,
    val exercises: LinkedList<WorkoutExercise> = LinkedList(),
    var totalDurationSeconds: Long = 0,
    var totalCalories: Float = 0F
    ){
    fun addExercise(exercise: WorkoutExercise){
        exercises.add(exercise)
        totalDurationSeconds += exercise.getLength()
        totalCalories += exercise.getExercise().calories * totalDurationSeconds
    }
    fun deleteExercise(exercise: WorkoutExercise){
        exercises.remove(exercise)
        totalDurationSeconds -= exercise.getLength()
        totalCalories -= exercise.getExercise().calories * totalDurationSeconds
    }

    fun getWorkoutDurationHourFormat(): String {

        var seconds: Long = totalDurationSeconds

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

    //drag to a new order
    //ie A B C D -> B C A D
    //input for the example above: A is the object and the input index is 2
    fun reorderExercise(exercise: WorkoutExercise, index: Int){
        exercises.remove(exercise)
        exercises.add(index, exercise)
    }
}