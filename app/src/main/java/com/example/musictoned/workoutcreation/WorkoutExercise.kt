package com.example.musictoned.workoutcreation

//input is workout name, time in seconds
class WorkoutExercise(workout: String) {
    private val workout = workout
    private var length = 30 //DEFAULT TIME OF 30s
    private var songSetting =
        SongSetting.SHUFFLE //DEFAULT SETTING OF SHUFFLE. 3 settings? shuffle, global shuffle and set song
    private var song = "" //song name in string? depends on what info needed to play the song

    fun getWorkout(): String {
        return workout
    }
    fun getTime(): Int {
        return length;
    }
    fun setTime(time: Int){
        length = time
    }
    fun getSongSetting(): SongSetting{
        return songSetting
    }
    fun setSongSetting(setting: SongSetting){
        songSetting = setting
    }
    fun getSong(): String{
        return song
    }
    fun setSong(s: String){
        song = s
    }
}