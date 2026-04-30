package com.ios.launcher.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightScheme = lightColorScheme(
    primary = Color(0xFF4A90E2),
    background = Color(0xFFEDF2FF),
    surface = Color(0x66FFFFFF)
)

private val DarkScheme = darkColorScheme(
    primary = Color(0xFF89B8FF),
    background = Color(0xFF0E1017),
    surface = Color(0x33232835)
)

@Composable
fun IOSLauncherTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkScheme else LightScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
