package com.example.musictoned.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.musictoned.R


val FontName = FontFamily(
    Font(R.font.latoregular, FontWeight.Normal),
    Font(R.font.latolight,FontWeight.Light),
    Font(R.font.latobold, FontWeight.SemiBold),
    Font(R.font.latobold, FontWeight.ExtraBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)