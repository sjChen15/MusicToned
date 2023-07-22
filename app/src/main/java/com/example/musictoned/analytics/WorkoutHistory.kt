package com.example.musictoned.analytics

import com.example.musictoned.util.LocalStorage
import com.example.musictoned.workoutcreation.Workout
import java.time.Clock
import java.time.LocalDate

object AllWorkoutHistory {
    var allWorkoutHistory: ArrayList<Workout> = LocalStorage.getAllWorkoutHistory()//LocalStorage.get
    var allWorkoutHistoryDates: ArrayList<LocalDate> = LocalStorage.getAllWorkoutHistoryDates()
    fun saveHistory(workout: Workout){//TODO: save at the end of a workout
        val currentDate = LocalDate.now(Clock.systemDefaultZone())
        allWorkoutHistory.add(workout)
        allWorkoutHistoryDates.add(currentDate)
        LocalStorage.writeAllWorkoutHistory(allWorkoutHistory)
        LocalStorage.writeAllWorkoutHistoryDates(allWorkoutHistoryDates)
    }

    //returns a list of workout from today in order of most recent
    fun getTodaysWorkouts():ArrayList<Workout>{
        val todaysWorkouts = arrayListOf<Workout>()

        val currentDate = LocalDate.now(Clock.systemDefaultZone())

        for (i in allWorkoutHistoryDates.indices){
            val date = allWorkoutHistoryDates[i]
            if(date.isBefore(currentDate)){
                break
            }
            if(date == currentDate){
                todaysWorkouts.add(allWorkoutHistory[i])
            }
        }
        return todaysWorkouts
    }

}