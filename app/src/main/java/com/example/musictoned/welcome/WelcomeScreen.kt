package com.example.musictoned.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musictoned.R
import com.example.musictoned.ui.theme.MusicTonedTheme
import com.example.musictoned.util.supportWideScreen

@Composable
fun WelcomeScreen(
    onClickCreateAnAccount: () -> Unit
) {
    Surface(modifier = Modifier
        .supportWideScreen()
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            Branding()
            Button(
                onClick = onClickCreateAnAccount,
                shape = RoundedCornerShape(size = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E60CE)),
                modifier = Modifier
                    .offset(x = 78.dp, y = 140.dp)
                    .shadow(elevation = 1.dp, spotColor = Color(0x33000000), ambientColor = Color(0x33000000))
                    .shadow(elevation = 2.dp, spotColor = Color(0x24000000), ambientColor = Color(0x24000000))
                    .shadow(elevation = 5.dp, spotColor = Color(0x1F000000), ambientColor = Color(0x1F000000))
                    .width(240.dp)
                    .height(55.dp)
                    .background(color = Color(0xFF5E60CE), shape = RoundedCornerShape(size = 4.dp))
                    .padding(start = 22.dp, top = 8.dp, end = 22.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "CREATE AN ACCOUNT",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.46.sp)
            }
        }
        BottomBranding()
    }
}

@Composable
private fun Branding() {
    Image(
        painter = painterResource(id = R.drawable.login_logo),
        contentDescription = "MusicToned logo",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .offset(x = 60.dp, y = 44.dp)
            .width(272.dp)
            .height(137.dp)
    )
    Text(
        text = "YOUR PERSONAL WORKOUT DJ",
        fontSize = 15.sp,
        fontFamily = FontFamily(Font(R.font.lato_regular)),
        fontWeight = FontWeight(700),
        fontStyle = FontStyle.Italic,
        color = Color(0xFF5E60CE),
        letterSpacing = 1.6.sp,
        modifier = Modifier
            .offset(x = 66.dp, y = 48.dp)
            .width(272.dp)
            .height(19.dp)
    )
    Text(
        text = "You do the workout,\n we pick the playlist.\n\n\n Upgrade your workout experience today by clicking the button below.",
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.lato_light)),
        fontWeight = FontWeight(300),
        textAlign = TextAlign.Center,
        letterSpacing = 0.46.sp,
        lineHeight = 32.sp,
        modifier = Modifier
            .offset(x = 60.dp, y = 100.dp)
            .width(274.dp)
            .height(220.dp)
        )
}

@Composable
fun BottomBranding() {
    Image(
        painter = painterResource(id = R.drawable.blue_wave),
        contentDescription = "Light blue wave",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .offset(x = 0.dp, y = 311.dp)
            .width(1200.dp)
            .height(0.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.dark_blue_wave),
        contentDescription = "Dark blue wave",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .offset(x = 0.dp, y = 348.dp)
            .width(1200.dp)
            .height(800.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.purple_wave),
        contentDescription = "Purple wave",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .offset(x = 0.dp, y = 380.dp)
            .width(361.dp)
            .height(87.48515.dp)
    )
}

@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RoutinesScreenPreview() {
    MusicTonedTheme {
        WelcomeScreen(
            onClickCreateAnAccount = {}
        )
    }
}