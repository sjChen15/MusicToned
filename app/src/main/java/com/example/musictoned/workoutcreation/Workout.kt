package com.example.musictoned.workoutcreation

import java.util.LinkedList

data class Workout (var name: String, val exercises: LinkedList<WorkoutExercise> = LinkedList() ){
    fun saveExercises(newExercises: List<WorkoutExercise>) {
        exercises.clear()
        exercises.addAll(newExercises)
    }

    fun getWorkoutDurationHourFormat(): String{

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

        if(hours >= 10){
            hourPrefix = ""
        }
        if(minutes >= 10){
            minutePrefix = ""
        }
        if(seconds >= 10){
            secondPrefix = ""
        }

        totalTime = "$hourPrefix$hours:$minutePrefix$minutes:$secondPrefix$seconds"

        return totalTime
    }
}