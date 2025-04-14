package io.siffert.mobile.app.inventory.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InventoryNavigationSuiteScaffold(
    navigationSuiteItems: InventoryNavigationSuiteScope.() -> Unit,
    modifier :Modifier= Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
){
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = InventoryNavigationColorDefaults.navigationSelectedItemColor(),
            unselectedIconColor = InventoryNavigationColorDefaults.navigationContentColor(),
            selectedTextColor = InventoryNavigationColorDefaults.navigationSelectedItemColor(),
            unselectedTextColor = InventoryNavigationColorDefaults.navigationContentColor(),
            indicatorColor = InventoryNavigationColorDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = InventoryNavigationColorDefaults.navigationSelectedItemColor(),
            unselectedIconColor = InventoryNavigationColorDefaults.navigationContentColor(),
            selectedTextColor = InventoryNavigationColorDefaults.navigationSelectedItemColor(),
            unselectedTextColor = InventoryNavigationColorDefaults.navigationContentColor(),
            indicatorColor = InventoryNavigationColorDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = InventoryNavigationColorDefaults.navigationSelectedItemColor(),
            unselectedIconColor = InventoryNavigationColorDefaults.navigationContentColor(),
            selectedTextColor = InventoryNavigationColorDefaults.navigationSelectedItemColor(),
            unselectedTextColor = InventoryNavigationColorDefaults.navigationContentColor(),
        ),
    )
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            InventoryNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = InventoryNavigationColorDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

/**
 * A wrapper around [NavigationSuiteScope] to declare navigation items.
 */
class InventoryNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

/**
 * Inventory navigation default values.
 */
object InventoryNavigationColorDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
