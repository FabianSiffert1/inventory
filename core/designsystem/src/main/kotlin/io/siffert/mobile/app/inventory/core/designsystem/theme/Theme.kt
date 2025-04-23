package io.siffert.mobile.app.inventory.core.designsystem.theme

import android.graphics.Color.*
import android.os.Build
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)


@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)


val LightAndroidGradientColors = GradientColors(container = DarkGreenGray95)
val DarkAndroidGradientColors = GradientColors(container = Color.Black)

val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkGreenGray95)
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

@Composable
fun InventoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }

    // Gradient colors
    // use when gradient is disabled specifically, not implemented yet
    // val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )
    val gradientColors = when {
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        else -> defaultGradientColors
    }

    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalGradientColors provides gradientColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

data class ThemeSettings(
    val darkTheme: Boolean,
)

val lightScrim = argb(0xe6, 0xFF, 0xFF, 0xFF)

val darkScrim = argb(0x80, 0x1b, 0x1b, 0x1b)