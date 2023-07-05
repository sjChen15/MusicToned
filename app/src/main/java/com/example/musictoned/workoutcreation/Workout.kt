package com.example.musictoned.workoutcreation

import java.util.LinkedList

data class Workout (var name: String, val exercises: LinkedList<WorkoutExercise> = LinkedList() ){
    fun addExercise(exercise: WorkoutExercise){
        exercises.add(exercise)
    }
    fun deleteExercise(exercise: WorkoutExercise){
       exercises.remove(exercise)
    }

    fun deleteLastExercise(){
        val s = exercises.size
        if ( s > 1 && exercises[s-1].getExercise().name == exercises[s-2].getExercise().name ){
            exercises.removeLast()
        }
    }

    //drag to a new order
    //ie A B C D -> B C A D
    //input for the example above: A is the object and the input index is 2
    fun reorderExercise(exercise: WorkoutExercise, index: Int){
        exercises.remove(exercise)
        exercises.add(index, exercise)
    }
}