package com.example.musictoned.workoutcreation

//input is workout name, time in seconds
data class WorkoutExercise(
    private val workout: Exercise,
    private var length: Int = 30,
    private var songSetting: SongSetting = SongSetting.SHUFFLE,
    private var bpmMode: BpmMode = BpmMode.AVERAGE,
    private var song: String = "" //song name in string? depends on what info needed to play the song
){
    fun getExercise(): Exercise{
        return workout
    }

    fun getLength(): Int{
        return length
    }

    fun getSong(): String{
        return song
    }

    fun getBpmMode(): BpmMode{
        return bpmMode
    }
}