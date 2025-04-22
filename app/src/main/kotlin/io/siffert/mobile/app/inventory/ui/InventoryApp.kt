package io.siffert.mobile.app.inventory.ui

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryNavigationSuiteScaffold

@Composable
fun InventoryApp(
    appState: InventoryAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    InventoryApp(appState = appState, windowAdaptiveInfo = windowAdaptiveInfo)
}


@Composable
internal fun InventoryApp(appState: InventoryAppState,
                          windowAdaptiveInfo: WindowAdaptiveInfo) {
    //todo: use appstate to iterate over topleveldestinations
    val foo = appState
    InventoryNavigationSuiteScaffold(
        navigationSuiteItems = { -> },
        windowAdaptiveInfo = windowAdaptiveInfo,
    )
    {
    }
}
