package com.example.musictoned.util

import android.content.Context
import com.example.musictoned.MainActivity
import com.example.musictoned.workoutcreation.Workout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*




object LocalStorage {

    private lateinit var app: MainActivity
    fun setApplication(mainActivity: MainActivity) {
        app = mainActivity
    }
    private val gson = Gson()
    private const val workoutsFilename = "workouts.json"

    /**
     * Ref: https://www.bezkoder.com/kotlin-parse-json-gson/
     * Ref: https://www.javatpoint.com/kotlin-android-read-and-write-internal-storage
     */
    fun getWorkouts(): ArrayList<Workout>{
        //check if file exists, if not it is the first run of the app
        val file:File = app.applicationContext.getFileStreamPath(workoutsFilename)
        if(!file.exists()){
            return arrayListOf()
        }

        //string of a list of json objects
        val jsonString = app.applicationContext.openFileInput(workoutsFilename).bufferedReader().use{it.readText()}
        println("Reading file!")
        println(jsonString)
        //convert string to list of Workouts
        val listWorkoutType = object : TypeToken<ArrayList<Workout>>() {}.type

        return gson.fromJson(jsonString, listWorkoutType)

    }
    /**
     * Ref: https://www.bezkoder.com/kotlin-parse-json-gson/
     * Ref: https://www.javatpoint.com/kotlin-android-read-and-write-internal-storage
     */
    fun writeWorkouts(workouts: ArrayList<Workout>){
        //convert list of workouts to string of list of jsons
        val jsonWorkoutsList: String = gson.toJson((workouts))
        println(jsonWorkoutsList)
        //write to local storage
        try {
            //creates file if it does not already exists
            //overwrites file if it does exist
            val fileOutputStream: FileOutputStream = app.applicationContext.openFileOutput(workoutsFilename,Context.MODE_PRIVATE)
            fileOutputStream.write(jsonWorkoutsList.toByteArray())
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

    }
}