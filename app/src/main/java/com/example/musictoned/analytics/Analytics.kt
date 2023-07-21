package com.example.musictoned.analytics

import java.time.LocalDate

object Analytics {
    //TODO: depedning on mock, get the hours worked
    var workoutStreak: Int = getWorkoutStreakFromHistory()
    var caloriesBurnedToday: Float = getCaloriesBurned()
    var recentActivity: List<Float> = getRecentActivityHours() //list of hours worked in the current week, sun-sat
    private fun getWorkoutStreakFromHistory(): Int{
        var currentDate: LocalDate = LocalDate.now().minusDays(1) //start at yesterday looking for streaks
        var streak = 0
        for(workoutHistory in AllWorkoutHistory.allWorkoutHistory.reversed()){
            if(workoutHistory.date.isBefore(currentDate)){ //streak broken
                break
            }
            if(workoutHistory.date == currentDate){
                currentDate = currentDate.minusDays(1)
                streak += 1
            }
            //if the workout date is after the current date, continue/do nothing
        }

        return streak
    }

    private fun getCaloriesBurned(): Float{
        val todaysWorkouts = AllWorkoutHistory.getTodaysWorkouts()
        var calories = 0F
        for(workoutHistory in todaysWorkouts){
            calories += workoutHistory.workout.totalCalories
        }
        return calories
    }

    private fun getRecentActivityHours(): List<Float>{
        val recent = listOf(0F,0F,0F,0F,0F,0F,0F)
        //TODO: actually get data
        return recent
    }
    fun updateAnalytics(){
        caloriesBurnedToday = getCaloriesBurned()
        recentActivity = getRecentActivityHours()
    }

}