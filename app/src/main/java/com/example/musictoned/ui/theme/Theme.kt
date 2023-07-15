package com.example.musictoned.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.musictoned.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MusicTonedTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun BottomWaves(
    withBottomBar: Boolean = true
){

    //val configuration = LocalConfiguration.current

    //val screenHeight = configuration.screenHeightDp.dp
    //Log.d("SCREEN HEIGHT: ", screenHeight.toString())

    var componentHeight by remember { mutableStateOf(0.dp) }
    //Log.d("IMAGE HEIGHT", componentHeight.toString())

    // get local density from composable
    val density = LocalDensity.current

    val yOffset: Dp = if(withBottomBar){
        567.dp
    } else {
        567.dp + BOTTOM_BAR_HEIGHT.dp
    }

    /* Reference for using onGloballyPosition: https://medium.com/@vontonnie/how-to-get-the-height-or-width-of-an-element-in-jetpack-compose-8af04365d555*/

    Image(
        painter = painterResource(id = R.drawable.routines_waves),
        contentDescription = "Routines Waves",
        alignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                componentHeight = with(density) {
                    it.size.height.toDp()
                }
            }
            //I think the top and bottom bars of the phone that are not part of our app
            //are affecting the calculation of the screen height (The blue bar at the top, and the
            //black bar at the bottom)
            //either that, or it's offsetting from the lazy grid
            //.offset(x = 0.dp, y = screenHeight - ( BOTTOM_BAR_HEIGHT.dp + componentHeight))
            .offset(x = 0.dp, y = yOffset)
            .background(color = Color.Transparent),
        contentScale = ContentScale.FillWidth,
    )
}