package com.example.musictoned.util

import com.example.musictoned.MainActivity
import com.example.musictoned.workoutcreation.Workout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


object LocalStorage {

    private lateinit var app: MainActivity
    fun setApplication(mainActivity: MainActivity) {
        app = mainActivity
    }
    private val gson = Gson()
    private const val workoutsFilename = "workouts.json"

    /**
     * Ref: https://www.bezkoder.com/kotlin-parse-json-gson/
     */
    fun getWorkouts(): ArrayList<Workout>{
        //string of a list of json objects
        val jsonString = app.applicationContext.openFileInput(workoutsFilename).bufferedReader().use{it.readText()}

        //convert string to list of Workouts
        val listWorkoutType = object : TypeToken<ArrayList<Workout>>() {}.type

        return gson.fromJson(jsonString, listWorkoutType)
    }
    /**
     * Ref: https://www.bezkoder.com/kotlin-parse-json-gson/
     */
    fun writeWorkouts(workouts: ArrayList<Workout>){
        //convert list of workouts to string of list of jsons
        val jsonWorkoutsList: String = gson.toJson((workouts))

        //write to local storage
        File(workoutsFilename).writeText(jsonWorkoutsList)
    }
}