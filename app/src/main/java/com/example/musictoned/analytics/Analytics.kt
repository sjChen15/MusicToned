package com.example.musictoned.analytics

import java.time.Clock
import java.time.LocalDate

object Analytics {
    //TODO: depedning on mock, get the hours worked
//    var workoutStreak: Int = getWorkoutStreakFromHistory()
//    var caloriesBurnedToday: Float = getCaloriesBurned()
//    var recentActivity: List<Float> = getRecentActivityHours() //list of hours worked in the current week, sun-sat
    fun getWorkoutStreakFromHistory(): Int{
        var currentDate: LocalDate = LocalDate.now(Clock.systemDefaultZone()).minusDays(1) //start at yesterday looking for streaks
        var streak = 0
        for(workoutHistoryDate in AllWorkoutHistory.allWorkoutHistoryDates.reversed()){
            if(workoutHistoryDate.isBefore(currentDate)){ //streak broken
                break
            }
            if(workoutHistoryDate == currentDate){
                currentDate = currentDate.minusDays(1)
                streak += 1
            }
            //if the workout date is after the current date, continue/do nothing
        }
        return streak
    }

    fun getCaloriesBurned(): Float{
        val todaysWorkouts = AllWorkoutHistory.getTodaysWorkouts()
        var calories = 0F
        for(workout in todaysWorkouts){
            calories += workout.totalCalories
        }
        return calories
    }

    fun getRecentActivityHours(): List<Float>{
        val recent = listOf(1F,8F,3F,2F,1F,0F,0F)


        return recent
    }

//    fun updateAnalytics(){
//        caloriesBurnedToday = getCaloriesBurned()
//        recentActivity = getRecentActivityHours()
//    }

}