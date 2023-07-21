package com.example.musictoned.analytics

import com.example.musictoned.util.LocalStorage
import com.example.musictoned.workoutcreation.Workout
import java.time.LocalDate

object AllWorkoutHistory {
    var allWorkoutHistory: ArrayList<WorkoutHistory> = LocalStorage.getAllWorkoutHistory()//LocalStorage.get

    fun saveHistory(workout: Workout){//TODO: save at the end of a workout
        val currentDate = LocalDate.now()
        allWorkoutHistory.add(WorkoutHistory(workout,currentDate))
        LocalStorage.writeAllWorkoutHistory(allWorkoutHistory)
    }

    //returns a list of workoutHistory from today in order of most recent
    fun getTodaysWorkouts():ArrayList<WorkoutHistory>{
        val todaysWorkouts = arrayListOf<WorkoutHistory>()

        val currentDate = LocalDate.now()

        for(workoutHistory in allWorkoutHistory.reversed()){
            if(workoutHistory.date.isBefore(currentDate)){ //no longer today's workouts
                break
            }
            if(workoutHistory.date == currentDate){
                todaysWorkouts.add(workoutHistory)
            }
        }
        return todaysWorkouts
    }

}

//class for one workout history object
data class WorkoutHistory(
    val workout: Workout,
    val date: LocalDate
)