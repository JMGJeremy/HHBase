package com.jmg.baseproject

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)



val HHHPrimaryColor = Color(android.graphics.Color.parseColor("#F5F5F5"))
val LightGray = Color(red = 240, green = 240, blue = 240)
val SlideOutMenuGrey = Color(red = 0f, green = 0f, blue = 0f, alpha = .6f)
val HHHSecondaryColor = Color(android.graphics.Color.parseColor("#005F73"))
val ButtonBackground = Color(android.graphics.Color.parseColor("#005F73"))

private val LightColorScheme = lightColorScheme(
    primary = HHHPrimaryColor,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = LightGray,
    onSurface = Gray
)


val DroidFontFamily = FontFamily(
    Font(R.font.droid_sans)
)

val DroidFontBold = FontFamily(
    Font(R.font.droid_sans_bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = DroidFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(arrayListOf(Font(com.jmg.baseproject.R.font.droid_sans_bold))),
        color = Color.White
    )
)

@Composable
fun HHBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
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