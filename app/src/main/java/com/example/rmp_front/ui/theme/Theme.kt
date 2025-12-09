package com.example.rmp_front.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = BackgroundDark,
    onPrimary = TextPrimary,
    onSecondary = TextSecondary,
    primary = BaseColor,
    error = ErrorColor,
    onSurface = PlaceholderText,
    tertiary = GradientDark,
    onTertiary = PlaceholderText,
)

private val LightColorScheme = lightColorScheme(
    background = BackgroundLight,
    onPrimary = PrimaryLight,
    onSecondary = SecondaryLight,
    primary = BaseLight,
    error = ErrorColor,
    onSurface = AccentLight,
    tertiary = GradientLight,
    onTertiary = PlaceholderText,
)
@Composable
fun RMP_FRONTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {


        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}