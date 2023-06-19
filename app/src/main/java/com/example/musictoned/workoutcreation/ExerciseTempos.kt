package com.example.musictoned.workoutcreation

import android.content.res.AssetManager
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.io.InputStream


//singleton
object ExerciseTempos{
    private const val filename = "exercise-tempos.txt"
    private val exerciseToTemposMap = mapOf<String,Int>(
        "push-up" to 80,
        "sit-up" to 100,
        "jumping-jack" to 120
    )

    //returns tempo of exercise
    fun getTempo(exercise:String): Int {
        return exerciseToTemposMap.getValue(exercise)
    }

}
