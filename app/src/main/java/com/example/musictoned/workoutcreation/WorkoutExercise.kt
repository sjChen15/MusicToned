package com.example.musictoned.workoutcreation

//input is workout name, time in seconds
data class WorkoutExercise(
    private val exercise: Exercise,
    private var length: Long = 30,
    private var songSetting: SongSetting = SongSetting.SHUFFLE,
    private var bpmMode: BpmMode = BpmMode.AVERAGE,
    private var song: String = "" //song name in string? depends on what info needed to play the song
){
    fun getExercise(): Exercise{
        return exercise
    }

    fun getLength(): Long{
        return length
    }

    fun setLength(l: Long){
        length = l
    }

    fun getSong(): String{
        return song
    }

    fun setSong(s: String){
        song = s
    }

    fun getBpmMode(): BpmMode{
        return bpmMode
    }

    fun setBpmMode(bpm: BpmMode){
        bpmMode = bpm
    }
}