package com.example.musictoned.workoutcreation

import com.example.musictoned.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type
import java.util.stream.Collectors

//singleton
object ExerciseTempos{
    private const val exerciseTemposFolder= "ExerciseTempos/"
    private val filenames = listOf<String>("armExercises","legExercises","backExercises","coreExercises","cardioExercises")
    /**
     * Keep lists of exercises organized into categories by filename and also a master list of all exercises
     */

    //exercise name to exercise object
    private var allExercises: MutableMap<String,Exercise> = mutableMapOf()

    //map of
    // key: file name ie legExercises to
    // value: list of leg exercises
    private var sortedExercises: MutableMap<String,List<Exercise>> = mutableMapOf()

    /**
     * Ref: https://www.bezkoder.com/kotlin-android-read-json-file-assets-gson/
     */

    private val exerciseMapType: Type = object : TypeToken<List<Exercise>>() {}.type
    private lateinit var app: MainActivity
    fun setApplication(mainActivity: MainActivity){
        app = mainActivity
        for(name in filenames){
            var jsonString = ""
            try{
                jsonString = app.applicationContext.assets.open("$exerciseTemposFolder$name.json").bufferedReader().use{it.readText()}
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
            val exerciseList: List<Exercise> = Gson().fromJson(jsonString, exerciseMapType)

            allExercises.putAll(exerciseList.associateBy { it.name })
            sortedExercises[name] = exerciseList
        }
    }

    //get specific Exercise object
    fun getExercise(exercise: String): Exercise {
        return allExercises.getValue(exercise)
    }

    fun getAllArmExercises(): List<Exercise> {
        return sortedExercises.getValue("armExercises")
    }
    fun getAllLegExercises(): List<Exercise> {
        return sortedExercises.getValue("legExercises")
    }

    fun getAllBackExercises(): List<Exercise>{
        return sortedExercises.getValue("backExercises")
    }

    fun getAllCoreExercises(): List<Exercise>{
        return sortedExercises.getValue("coreExercises")
    }

    fun getAllCardioExercises(): List<Exercise>{
        return sortedExercises.getValue("cardioExercises")
    }
}
