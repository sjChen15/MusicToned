package com.example.musictoned.workoutcreation

import java.io.File

//singleton
object exerciseTempoMapping{
    private const val filename = "exercise-tempos.txt"
    private val exerciseToTemposMap = mutableMapOf<String,Int>()

    //get mapping on init and always use this object
    init{
        File(filename).forEachLine {
            val splitString = it.split(", ")
            val exercise = splitString[0]
            val tempo = splitString[1].toInt()
            if (splitString.isNotEmpty()){
                exerciseToTemposMap[exercise] = tempo
            }
        }
    }

    //returns tempo of exercise
    fun getTempo(exercise:String): Int {
        return exerciseToTemposMap.getValue(exercise)
    }

}
