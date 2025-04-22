package io.siffert.mobile.app.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.inventory.ui.InventoryApp
import io.siffert.mobile.app.inventory.ui.rememberInventoryAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberInventoryAppState()
            InventoryTheme {
                        InventoryApp(appState = appState)
                    }
                }

    }
}