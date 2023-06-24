package com.example.musictoned.workoutcreation

//input is workout name, time in seconds
data class WorkoutExercise(private val workout: String, private var length: Int = 30, private var songSetting: SongSetting = SongSetting.SHUFFLE) {
    private var song = "" //song name in string? depends on what info needed to play the song
}