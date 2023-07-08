package com.example.musictoned.workoutcreation

data class Exercise (
    var name: String = "",
    var target: List<String> = listOf(),
    var bpm: Int = 0,
    var imageName: String = ""
)