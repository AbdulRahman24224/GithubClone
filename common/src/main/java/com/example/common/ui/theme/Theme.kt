package com.example.common.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.common.ui.AppColors


private val DarkColorScheme = darkColorScheme(
    primary = AppColors.Black,
    secondary = AppColors.Orange,
    tertiary = AppColors.Outcome,
    background = AppColors.Black,
    surface = AppColors.Outcome,
    onPrimary = AppColors.Primary600,
    onSecondary = AppColors.White,
    onTertiary = AppColors.White,
    onBackground = AppColors.Grey100,
    onSurface = AppColors.White,
)

private val LightColorScheme = lightColorScheme(
    primary = AppColors.White,
    secondary = AppColors.Primary600,
    tertiary = AppColors.Outcome,
    background = AppColors.White,
    surface = AppColors.LightOrange,
    onPrimary = AppColors.Primary400,
    onSecondary = AppColors.Grey8,
    onTertiary = AppColors.White,
    onBackground = AppColors.Grey100,
    onSurface = AppColors.Black,
)

@Composable
fun GithubCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

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