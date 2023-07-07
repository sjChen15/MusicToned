package com.example.musictoned.profile

import com.example.musictoned.util.LocalStorage

enum class WeightUnit {
    KG,
    LB
}

enum class HeightUnit {
    CM,
    FT
}

enum class Goals {
    GAIN_MUSCLE,
    IMPROVE_ENDURANCE,
    LOSE_WEIGHT,
    INCREASE_FLEXIBILITY,
    EXERCISE_REGULARLY
}
//singleton profile to be used
object Profile{
    val profile: ProfileClass = LocalStorage.getProfile()
}

data class ProfileClass(
    var name: String = "",
    var age: Short = 0,
    var weight: Pair<Int, WeightUnit> = Pair(0,WeightUnit.LB),
    var height: Pair<Int, HeightUnit> = Pair(0, HeightUnit.CM),
    var goals: ArrayList<Goals> = arrayListOf()
    ) {

}