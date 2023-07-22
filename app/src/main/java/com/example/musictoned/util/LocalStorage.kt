package com.example.musictoned.util

import android.content.Context
import com.example.musictoned.MainActivity
import com.example.musictoned.profile.ProfileClass
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.ExerciseTempos
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.*
import java.time.Clock
import java.time.LocalDate

object LocalStorage {

    private lateinit var app: MainActivity
    fun setApplication(mainActivity: MainActivity) {
        app = mainActivity
        writeWorkouts(arrayListOf())
        //deleteProfile()

        //Create some fake streak data
        val date = LocalDate.now(Clock.systemDefaultZone())
        println("LOCAL STORAGE")
        val w = Workout("Hello")
        val e = ExerciseTempos.getExercise("Deadlifts")
        w.addExercise(WorkoutExercise(e,length = 4600))
        writeAllWorkoutHistory( arrayListOf(w,w,w))
        writeAllWorkoutHistoryDates(arrayListOf(date.minusDays(3),date.minusDays(2),date.minusDays(1)))

    }
    private val localDateGson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter().nullSafe()).create()
    private val gson = Gson()
    private const val workoutsFilename = "workouts.json"
    private const val profileFilename = "profile.json"
    private const val workoutHistoryFilename = "workoutHistory.json"
    private const val workoutHistoryDatesFilename = "workoutHistoryDates.json"

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
        //convert string to list of Workouts
        val listWorkoutType = object : TypeToken<ArrayList<Workout>>() {}.type

        return gson.fromJson(jsonString, listWorkoutType)
    }

    /**
     * Obtains the drawable resource ID used to render an image by providing a dynamic string of the resource name
     */
    fun getExerciseImage(imageName: String): Int {
        return app.applicationContext.resources.getIdentifier(imageName, "drawable", app.applicationContext.packageName)
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

    //Deletes the old workouts.json file and writes a new one with the updated 'allWorkouts' ArrayList
    fun reWriteWorkoutsFile(){
        writeWorkouts(AllWorkouts.getAllWorkouts())
    }

    //check if profile exists
    fun doesProfileExist(): Boolean = app.applicationContext.getFileStreamPath(profileFilename).exists()

    fun getProfile(): ProfileClass {
        //check if file exists
        val file:File = app.applicationContext.getFileStreamPath(profileFilename)
        if(!file.exists()){
            return ProfileClass()
        }

        val jsonString = app.applicationContext.openFileInput(profileFilename).bufferedReader().use{it.readText()}

        //convert string to profile
        val listWorkoutType = object : TypeToken<ProfileClass>() {}.type

        return gson.fromJson(jsonString, listWorkoutType)
    }

    //save profile to file
    fun writeProfile(profile: ProfileClass){
        val jsonWorkoutsList: String = gson.toJson(profile)
        //write to local storage
        try {
            //creates file if it does not already exists
            //overwrites file if it does exist
            val fileOutputStream: FileOutputStream = app.applicationContext.openFileOutput(profileFilename,Context.MODE_PRIVATE)
            fileOutputStream.write(jsonWorkoutsList.toByteArray())
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteProfile(){
        val file:File = app.applicationContext.getFileStreamPath(profileFilename)
        file.delete()
    }

    fun getAllWorkoutHistory(): ArrayList<Workout>{
        //check if file exists, if not it is the first run of the app
        val file:File = app.applicationContext.getFileStreamPath(workoutHistoryFilename)
        if(!file.exists()){
            return arrayListOf()
        }
        //string of a list of json objects
        val jsonString = app.applicationContext.openFileInput(workoutHistoryFilename).bufferedReader().use{it.readText()}

        //convert string to list of WorkoutHistory
        val listWorkoutHistoryType = object : TypeToken<ArrayList<Workout>>() {}.type

        return gson.fromJson(jsonString, listWorkoutHistoryType)
    }

    fun writeAllWorkoutHistory(allWorkoutHistory: ArrayList<Workout>){
        val jsonWorkoutsList: String = gson.toJson(allWorkoutHistory)
        //write to local storage
        try {
            //creates file if it does not already exists
            //overwrites file if it does exist
            val fileOutputStream: FileOutputStream = app.applicationContext.openFileOutput(
                workoutHistoryFilename,Context.MODE_PRIVATE)
            fileOutputStream.write(jsonWorkoutsList.toByteArray())
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllWorkoutHistoryDates(): ArrayList<LocalDate>{
        //check if file exists, if not it is the first run of the app
        val file:File = app.applicationContext.getFileStreamPath(workoutHistoryDatesFilename)
        if(!file.exists()){
            return arrayListOf()
        }
        //string of a list of json objects
        val jsonString = app.applicationContext.openFileInput(workoutHistoryDatesFilename).bufferedReader().use{it.readText()}

        //convert string to list of WorkoutHistory
        val listDateTimeType = object : TypeToken<ArrayList<LocalDate>>() {}.type

        return localDateGson.fromJson(jsonString, listDateTimeType)
    }

    fun writeAllWorkoutHistoryDates(dates: ArrayList<LocalDate>){
        val jsonWorkoutDatesList: String = localDateGson.toJson(dates)
        //write to local storage
        try {
            //creates file if it does not already exists
            //overwrites file if it does exist
            val fileOutputStream: FileOutputStream = app.applicationContext.openFileOutput(
                workoutHistoryDatesFilename,Context.MODE_PRIVATE)
            fileOutputStream.write(jsonWorkoutDatesList.toByteArray())
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
