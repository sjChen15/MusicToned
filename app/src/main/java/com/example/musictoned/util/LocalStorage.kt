package com.example.musictoned.util

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.musictoned.MainActivity
import com.example.musictoned.profile.ProfileClass
import com.example.musictoned.workoutcreation.AllWorkouts
import com.example.musictoned.workoutcreation.ExerciseTempos
import com.example.musictoned.workoutcreation.Workout
import com.example.musictoned.workoutcreation.WorkoutExercise
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*
import java.time.Clock
import java.time.LocalDate

object LocalStorage {

    private lateinit var app: MainActivity
    private lateinit var lifecycleScope: LifecycleCoroutineScope
    fun setApplication(mainActivity: MainActivity, cycleScope: LifecycleCoroutineScope) {
        app = mainActivity
        lifecycleScope = cycleScope
        writeWorkouts(arrayListOf())
        //deleteProfile()

        //Create some fake streak and recent activity data
        val date = LocalDate.now(Clock.systemDefaultZone())
        val w = Workout("Hello")
        val e = ExerciseTempos.getExercise("Hanging Leg Raise")
        val we =WorkoutExercise(e,length = 3600)
        w.saveExercises(listOf(we,we,we,we,we))
        writeAllWorkoutHistory( arrayListOf(w,w,w,w,w))
        writeAllWorkoutHistoryDates(arrayListOf(date.minusDays(3),date.minusDays(2),date.minusDays(1),date,date))

    }
    private val localDateGson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter().nullSafe()).create()
    private val gson = Gson()
    private const val workoutsFilename = "workouts.json"
    private const val profileFilename = "profile.json"
    private const val workoutHistoryFilename = "workoutHistory.json"
    private const val workoutHistoryDatesFilename = "workoutHistoryDates.json"
    private const val progressPicsFoldername = "progressPics"

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
        //creates file if it does not already exists
        //overwrites file if it does exist
        app.applicationContext.openFileOutput(workoutsFilename,MODE_PRIVATE).use {
            it.write(jsonWorkoutsList.toByteArray())
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
        //creates file if it does not already exists
        //overwrites file if it does exist
        app.applicationContext.openFileOutput(profileFilename,MODE_PRIVATE).use {
            it.write(jsonWorkoutsList.toByteArray())
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
                workoutHistoryFilename,MODE_PRIVATE)
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
                workoutHistoryDatesFilename,MODE_PRIVATE)
            fileOutputStream.write(jsonWorkoutDatesList.toByteArray())
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }


    //Ref: https://youtu.be/EeLz1DPMsW8?t=575
    suspend fun loadPhotoFromDate(date: LocalDate): InternalStoragePhoto?{
        return withContext(Dispatchers.IO){
            val files = app.applicationContext.filesDir.listFiles()
            files?.filter{it.canRead() && it.isFile && it.name.equals(date.toString())}?.map{
                val bytes = it.readBytes()
                val bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
                InternalStoragePhoto(it.name, bmp)
            }?.single()
        }
    }
    suspend fun loadAllPhotosFromInternalStorage(): List<InternalStoragePhoto>{
       return withContext(Dispatchers.IO){
           val files = app.applicationContext.filesDir.listFiles()
           files?.filter{it.canRead() && it.isFile && it.name.endsWith(".jpg")}?.map{
               val bytes = it.readBytes()
               val bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
               InternalStoragePhoto(it.name, bmp)
           } ?: listOf()
       }
    }

    fun loadPhotoFromInternalStorage(date:LocalDate): Bitmap{
        lifecycleScope.async{
            val img: InternalStoragePhoto? = loadPhotoFromDate(date)

            if (img != null) {
                return@async img.bitmap
            }
            return@async Bitmap.createBitmap(5,5,Bitmap.Config.ARGB_8888)
        }
        return Bitmap.createBitmap(5,5,Bitmap.Config.ARGB_8888)
    }

    //filename should be the date
    fun saveProgressPic(date: LocalDate, bmp: Bitmap): Boolean{ //returns true if saved
        return try {
            app.applicationContext.openFileOutput("$progressPicsFoldername/$date.jpg", MODE_PRIVATE).use{stream ->
                if(!bmp.compress(Bitmap.CompressFormat.JPEG, 95, stream)){
                    throw IOException("Couldn't save picture")
                }
            }
            true
        }catch(e:IOException){
            e.printStackTrace()
            false
        }
    }
}
