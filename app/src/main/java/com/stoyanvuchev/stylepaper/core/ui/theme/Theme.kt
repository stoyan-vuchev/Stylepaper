package com.stoyanvuchev.stylepaper.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.stoyanvuchev.systemuibarstweaker.ProvideSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.ScrimStyle
import com.stoyanvuchev.systemuibarstweaker.rememberSystemUIBarsTweaker

private val DarkColorScheme = darkColorScheme(
    primary = DarkThemeColors.md_theme_dark_primary,
    onPrimary = DarkThemeColors.md_theme_dark_onPrimary,
    primaryContainer = DarkThemeColors.md_theme_dark_primaryContainer,
    onPrimaryContainer = DarkThemeColors.md_theme_dark_onPrimaryContainer,
    secondary = DarkThemeColors.md_theme_dark_secondary,
    onSecondary = DarkThemeColors.md_theme_dark_onSecondary,
    secondaryContainer = DarkThemeColors.md_theme_dark_secondaryContainer,
    onSecondaryContainer = DarkThemeColors.md_theme_dark_onSecondaryContainer,
    tertiary = DarkThemeColors.md_theme_dark_tertiary,
    onTertiary = DarkThemeColors.md_theme_dark_onTertiary,
    tertiaryContainer = DarkThemeColors.md_theme_dark_tertiaryContainer,
    onTertiaryContainer = DarkThemeColors.md_theme_dark_onTertiaryContainer,
    error = DarkThemeColors.md_theme_dark_error,
    errorContainer = DarkThemeColors.md_theme_dark_errorContainer,
    onError = DarkThemeColors.md_theme_dark_onError,
    onErrorContainer = DarkThemeColors.md_theme_dark_onErrorContainer,
    background = DarkThemeColors.md_theme_dark_background,
    onBackground = DarkThemeColors.md_theme_dark_onBackground,
    surface = DarkThemeColors.md_theme_dark_surface,
    onSurface = DarkThemeColors.md_theme_dark_onSurface,
    surfaceVariant = DarkThemeColors.md_theme_dark_surfaceVariant,
    onSurfaceVariant = DarkThemeColors.md_theme_dark_onSurfaceVariant,
    outline = DarkThemeColors.md_theme_dark_outline,
    inverseOnSurface = DarkThemeColors.md_theme_dark_inverseOnSurface,
    inverseSurface = DarkThemeColors.md_theme_dark_inverseSurface,
    inversePrimary = DarkThemeColors.md_theme_dark_inversePrimary,
    surfaceTint = DarkThemeColors.md_theme_dark_surfaceTint
)

private val LightColorScheme = lightColorScheme(
    primary = LightThemeColors.md_theme_light_primary,
    onPrimary = LightThemeColors.md_theme_light_onPrimary,
    primaryContainer = LightThemeColors.md_theme_light_primaryContainer,
    onPrimaryContainer = LightThemeColors.md_theme_light_onPrimaryContainer,
    secondary = LightThemeColors.md_theme_light_secondary,
    onSecondary = LightThemeColors.md_theme_light_onSecondary,
    secondaryContainer = LightThemeColors.md_theme_light_secondaryContainer,
    onSecondaryContainer = LightThemeColors.md_theme_light_onSecondaryContainer,
    tertiary = LightThemeColors.md_theme_light_tertiary,
    onTertiary = LightThemeColors.md_theme_light_onTertiary,
    tertiaryContainer = LightThemeColors.md_theme_light_tertiaryContainer,
    onTertiaryContainer = LightThemeColors.md_theme_light_onTertiaryContainer,
    error = LightThemeColors.md_theme_light_error,
    errorContainer = LightThemeColors.md_theme_light_errorContainer,
    onError = LightThemeColors.md_theme_light_onError,
    onErrorContainer = LightThemeColors.md_theme_light_onErrorContainer,
    background = LightThemeColors.md_theme_light_background,
    onBackground = LightThemeColors.md_theme_light_onBackground,
    surface = LightThemeColors.md_theme_light_surface,
    onSurface = LightThemeColors.md_theme_light_onSurface,
    surfaceVariant = LightThemeColors.md_theme_light_surfaceVariant,
    onSurfaceVariant = LightThemeColors.md_theme_light_onSurfaceVariant,
    outline = LightThemeColors.md_theme_light_outline,
    inverseOnSurface = LightThemeColors.md_theme_light_inverseOnSurface,
    inverseSurface = LightThemeColors.md_theme_light_inverseSurface,
    inversePrimary = LightThemeColors.md_theme_light_inversePrimary,
    surfaceTint = LightThemeColors.md_theme_light_surfaceTint
)

@Suppress("DEPRECATION_ERROR")
@Composable
fun StylepaperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val tweaker = rememberSystemUIBarsTweaker()
    val navBarColor = when {
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.O && darkTheme -> Color.Unspecified
        Build.VERSION.SDK_INT <= Build.VERSION_CODES.O && !darkTheme -> seed
        else -> Color.Unspecified
    }

    DisposableEffect(tweaker, darkTheme) {
        tweaker.tweakSystemBarsStyle(
            statusBarStyle = tweaker.statusBarStyle.copy(
                darkIcons = !darkTheme,
                scrimStyle = ScrimStyle.None
            ),
            navigationBarStyle = tweaker.navigationBarStyle.copy(
                color = navBarColor,
                darkIcons = !darkTheme,
                scrimStyle = if (tweaker.isGestureNavigationEnabled) ScrimStyle.None
                else ScrimStyle.Custom(
                    lightThemeColor = seed,
                    darkThemeColor = seed
                )
            )
        )
        onDispose {}
    }

    val colorScheme = when {

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme

    }

    ProvideSystemUIBarsTweaker(
        systemUIBarsTweaker = tweaker,
        content = {

            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                content = content
            )

        }
    )

}