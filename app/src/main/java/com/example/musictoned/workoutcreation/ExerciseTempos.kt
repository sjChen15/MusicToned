package com.example.musictoned.workoutcreation

import com.example.musictoned.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

//singleton
object ExerciseTempos{
    private lateinit var app: MainActivity
    fun setApplication(mainActivity: MainActivity){
        app = mainActivity
    }
    private const val exerciseTemposFilename = "exerciseTempos.json"
    private var exerciseToTempoMap :Map<String,Int>
    /**
     * Ref: https://www.bezkoder.com/kotlin-android-read-json-file-assets-gson/
     */

    init{
        var jsonString : String = ""
        try{
            jsonString = app.applicationContext.assets.open(exerciseTemposFilename).bufferedReader().use{it.readText()}
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        //convert to map
        val exerciseTemposMapType = object : TypeToken<Map<String,Int>>() {}.type
        exerciseToTempoMap = Gson().fromJson(jsonString, exerciseTemposMapType)
    }

    //returns tempo of exercise
    fun getTempo(exercise:String): Int {
        return exerciseToTempoMap.getValue(exercise)
    }

}
