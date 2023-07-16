package com.example.musictoned.workoutcreation

import java.util.LinkedList

data class Workout (var name: String, val exercises: LinkedList<WorkoutExercise> = LinkedList() ){
    fun addExercise(exercise: WorkoutExercise){
        exercises.add(exercise)
    }
    fun deleteExercise(exercise: WorkoutExercise){
       exercises.remove(exercise)
    }

    fun getWorkoutDurationHourFormat(): String{

        var seconds: Long = 0
        var totalTime: String = "00:00:00"

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

    //drag to a new order
    //ie A B C D -> B C A D
    //input for the example above: A is the object and the input index is 2
    fun reorderExercise(exercise: WorkoutExercise, index: Int){
        exercises.remove(exercise)
        exercises.add(index, exercise)
    }
}