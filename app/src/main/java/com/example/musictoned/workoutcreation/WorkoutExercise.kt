package com.example.musictoned.workoutcreation

import com.example.musictoned.spotify.SpotifyConnect

//input is workout name, time in seconds
data class WorkoutExercise(
    private val exercise: Exercise,
    private var length: Long = 30, //in seconds
    private var songSetting: SongSetting = SongSetting.SHUFFLE,
    private var bpmMode: BpmMode = BpmMode.AVERAGE,
    private var song: String = "", //song name in string? depends on what info needed to play the song
    private var songID: String = ""
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

    fun getSongID(): String{
        return songID
    }
    fun setSong(s: String, id: String){
        song = s
        songID = id
    }

    fun setSongByBPM(bpm: BpmMode){
        val songInfo = SpotifyConnect.getSongInfo()
        var songId = ""
        var songName = ""

        //NEED TO ADD RANGE CHECK: if no song exists in that range, it will crash
        when (bpm) {
            BpmMode.SLOW -> {
                //get a slow song
                //PLACEHOLDER: need to put correct tempo range once we know what it should be
                songId = SpotifyConnect.getSongId(listOf(0.0, 100.0), songInfo)
            }
            BpmMode.AVERAGE -> {
                //get an average song
                //PLACEHOLDER: need to put correct tempo range once we know what it should be
                songId = SpotifyConnect.getSongId(listOf(100.0, 150.0), songInfo)
            }
            BpmMode.FAST -> {
                //get a fast song
                //PLACEHOLDER: need to put correct tempo range once we know what it should be
                songId = SpotifyConnect.getSongId(listOf(150.0, 200.0), songInfo)

            }
        }

        songName = SpotifyConnect.getSongName(songId)
        setSong(songName, songId)
    }

    fun getBpmMode(): BpmMode{
        return bpmMode
    }

    fun setBpmMode(bpm: BpmMode){
        bpmMode = bpm
    }
}