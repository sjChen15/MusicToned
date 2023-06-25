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
    private const val exerciseTemposFilename = "ExerciseTempos/armExercises.json"
    private var armExercises: Map<String,Exercise>
    /**
     * Ref: https://www.bezkoder.com/kotlin-android-read-json-file-assets-gson/
     */

    init{

        var jsonString = ""
        try{
            jsonString = app.applicationContext.assets.open(exerciseTemposFilename).bufferedReader().use{it.readText()}
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        val exerciseMapType = object : TypeToken<Map<String,Exercise>>() {}.type
        //convert to list
        armExercises = Gson().fromJson(jsonString, exerciseMapType)
    }

    //returns exercise object
    fun getArmExercise(exercise:String): Exercise {
        return armExercises.getValue(exercise)
    }
}
