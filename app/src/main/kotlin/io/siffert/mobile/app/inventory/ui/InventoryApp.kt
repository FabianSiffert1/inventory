package io.siffert.mobile.app.inventory.ui

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryNavigationSuiteScaffold
import kotlinx.serialization.Serializable

@Serializable
object InventoryApp


@Composable
fun InventoryApp(){
    AppLayout()
}



@Composable
internal fun AppLayout()   {
    InventoryNavigationSuiteScaffold(
        navigationSuiteItems = { -> },
        content = { -> },
        windowAdaptiveInfo = currentWindowAdaptiveInfo(),
    )
}
