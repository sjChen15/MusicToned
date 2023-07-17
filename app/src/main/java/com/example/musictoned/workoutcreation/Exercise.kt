package com.example.musictoned.workoutcreation

data class Exercise (
    var name: String = "",
    var target: List<String> = listOf(),
    var bpm: Int = 0,
    var calories: Float = 0F,
    var imageName: String = "",
)