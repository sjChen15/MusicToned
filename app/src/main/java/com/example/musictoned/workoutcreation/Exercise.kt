package com.example.musictoned.workoutcreation

import androidx.annotation.DrawableRes

data class Exercise (
    var name: String,
    var target: List<String>,
    var bpm: Int,
    @DrawableRes var imageId: Int
)