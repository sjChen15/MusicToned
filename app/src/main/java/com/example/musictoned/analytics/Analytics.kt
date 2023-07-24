package com.example.musictoned.analytics

import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

object Analytics {
//    var workoutStreak: Int = getWorkoutStreakFromHistory()
//    var caloriesBurnedToday: Float = getCaloriesBurned()
//    var recentActivity: List<Float> = getRecentActivityHours() //list of hours worked in the current week, sun-sat
    private val today: LocalDate = LocalDate.now(Clock.systemDefaultZone())
    fun getWorkoutStreakFromHistory(): Int{
        var currentDate = today.minusDays(1) //start at yesterday looking for streaks
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
        val recent = arrayListOf(0F,0F,0F,0F,0F,0F,0F)

        //recent activity from today until last sunday
        val lastSunday:LocalDate =  today.with(TemporalAdjusters.previousOrSame( DayOfWeek.SUNDAY )) //pos 0

        //get all history until sunday
        val reversedDates = AllWorkoutHistory.allWorkoutHistoryDates.reversed()
        val totalLength = reversedDates.size
        for (i in reversedDates.indices){
            val date = reversedDates[i]

            if(date.isBefore(lastSunday)){
                break
            }
            val daysSinceSunday =  lastSunday.until(date, ChronoUnit.DAYS)
            recent[daysSinceSunday.toInt()] += AllWorkoutHistory.allWorkoutHistory[totalLength - i - 1].totalDurationSeconds.div(3600F)
        }

        return recent
    }

}