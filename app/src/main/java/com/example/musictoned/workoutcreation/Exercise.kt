package com.example.musictoned.workoutcreation

data class Exercise (
    var name: String = "",
    var target: List<String> = listOf(),
    var MET: Float = 0F,
    var imageName: String = "",
)