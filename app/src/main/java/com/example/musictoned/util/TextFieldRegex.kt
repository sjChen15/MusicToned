package com.example.musictoned.util

object TextFieldRegex {
    val wholeNumberRegex: Regex = Regex("^\\d+\$")
    val decimalNumberRegex: Regex = Regex("^\\d*\\.?\\d*\$")
    val textOnlyRegex: Regex = Regex("^[a-zA-Z]*\$")
}