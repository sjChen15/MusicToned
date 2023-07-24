package com.example.musictoned.profile

import com.example.musictoned.util.LocalStorage

enum class WeightUnit {
    KG,
    LB
}

enum class HeightUnit {
    M,
    FT
}

//singleton profile to be used
object Profile{
    val profile: ProfileClass = LocalStorage.getProfile()
}

data class ProfileClass(
    var name: String = "",
    var age: Int = 0,
    var weight: Float = 0F,
    var height: Float = 0F,
    var weightUnit: WeightUnit = WeightUnit.KG,
    var heightUnit: HeightUnit = HeightUnit.M,
    var gainMuscle: Boolean = false,
    var improveEndurance: Boolean = false,
    var loseWeight: Boolean = false,
    var increaseFlexibility: Boolean = false,
    var exerciseRegularly: Boolean = false,
    var calorieGoal: Int = 0
    ){
    fun getNonZeroWeightInKG(): Float{
        if(weight != 0F){
            return if(weightUnit == WeightUnit.KG) weight else (weight*0.45).toFloat()
        }
        //if no weight specified use 70kg
        return 70F
    }

}