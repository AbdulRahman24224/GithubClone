package com.example.common.ui.bars

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.example.common.ui.AppColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangeStatusBarColor(
    statusBarColor: Color = MaterialTheme.colorScheme.surface,
    navigationBarColor: Color = MaterialTheme.colorScheme.primary,
    darkIcons: Boolean = isSystemInDarkTheme().not()
) {
    val systemUiController = rememberSystemUiController()
    statusBarColor?.let {
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = it,
                darkIcons = darkIcons
            )
        }
    }
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = true
        )
    }
}