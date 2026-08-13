package com.simrge.playview.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PlayGreen = Color(0xFF01875F)
private val PlayGreenLight = Color(0xFF4AB589)

private val LightColors = lightColorScheme(
    primary = PlayGreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4F5E8),
    onPrimaryContainer = Color(0xFF005C40),
    background = Color(0xFFF7FBF4),
    surface = Color(0xFFF7FBF4),
    surfaceVariant = Color(0xFFEEF3EC),
)

private val DarkColors = darkColorScheme(
    primary = PlayGreenLight,
    onPrimary = Color(0xFF005C40),
    primaryContainer = Color(0xFF0D3D2C),
    onPrimaryContainer = Color(0xFFD4F5E8),
    background = Color(0xFF111412),
    surface = Color(0xFF111412),
    surfaceVariant = Color(0xFF1C211E),
)

@Composable
fun PlayViewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
