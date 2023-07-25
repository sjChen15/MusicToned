package com.example.musictoned.analytics

import com.example.musictoned.util.LocalStorage
import com.example.musictoned.workoutcreation.Workout
import java.time.Clock
import java.time.LocalDate

object AllWorkoutHistory {
    var allWorkoutHistory: ArrayList<Workout> = LocalStorage.getAllWorkoutHistory()//LocalStorage.get
    var allWorkoutHistoryDates: ArrayList<LocalDate> = LocalStorage.getAllWorkoutHistoryDates()
    private val today = LocalDate.now(Clock.systemDefaultZone())
    fun saveHistory(workout: Workout){
        allWorkoutHistory.add(workout)
        allWorkoutHistoryDates.add(today)
        LocalStorage.writeAllWorkoutHistory(allWorkoutHistory)
        LocalStorage.writeAllWorkoutHistoryDates(allWorkoutHistoryDates)
    }

    //returns a list of workout from today in order of most recent
    fun getTodaysWorkouts():ArrayList<Workout>{
        val todaysWorkouts = arrayListOf<Workout>()

        val reversedDates = allWorkoutHistoryDates.reversed()
        for (i in reversedDates.indices){
            val date = reversedDates[i]
            if(date.isBefore(today)){
                break
            }
            if(date == today){
                todaysWorkouts.add(allWorkoutHistory[allWorkoutHistory.size - i -1])
            }
        }
        return todaysWorkouts
    }

}