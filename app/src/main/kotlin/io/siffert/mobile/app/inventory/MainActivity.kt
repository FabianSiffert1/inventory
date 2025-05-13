package io.siffert.mobile.app.inventory

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.util.trace
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.inventory.core.designsystem.theme.ThemeSettings
import io.siffert.mobile.app.inventory.core.designsystem.theme.darkScrim
import io.siffert.mobile.app.inventory.core.designsystem.theme.lightScrim
import io.siffert.mobile.app.inventory.ui.InventoryApp
import io.siffert.mobile.app.inventory.ui.rememberInventoryAppState
import io.siffert.mobile.app.inventory.util.isSystemInDarkTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val themeSettings by
            mutableStateOf(ThemeSettings(darkTheme = resources.configuration.isSystemInDarkTheme))

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                isSystemInDarkTheme().distinctUntilChanged().collect { darkTheme ->
                    trace("inventoryEdgeToEdge") {
                        enableEdgeToEdge(
                            statusBarStyle =
                                SystemBarStyle.auto(
                                    lightScrim = TRANSPARENT,
                                    darkScrim = TRANSPARENT,
                                ) {
                                    darkTheme
                                },
                            navigationBarStyle =
                                SystemBarStyle.auto(
                                    lightScrim = lightScrim,
                                    darkScrim = darkScrim,
                                ) {
                                    darkTheme
                                },
                        )
                    }
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            val appState = rememberInventoryAppState()
            InventoryTheme(darkTheme = themeSettings.darkTheme) {
                InventoryApp(appState = appState)
            }
        }
    }
}
