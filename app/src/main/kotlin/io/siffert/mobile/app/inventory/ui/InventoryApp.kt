package io.siffert.mobile.app.inventory.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import io.siffert.mobile.app.core.common.dialog.handling.DialogHost
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryBackground
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryGradientBackground
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryNavigationSuiteScaffold
import io.siffert.mobile.app.inventory.core.designsystem.theme.LocalGradientColors
import io.siffert.mobile.app.inventory.navigation.InventoryNavHost
import kotlin.reflect.KClass

@Composable
fun InventoryApp(
    appState: InventoryAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    InventoryBackground(modifier = modifier) {
        InventoryGradientBackground(gradientColors = LocalGradientColors.current) {
            InventoryAppLayout(appState = appState, windowAdaptiveInfo = windowAdaptiveInfo)
        }
    }
}

@Composable
internal fun InventoryAppLayout(
    appState: InventoryAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val currentDestination = appState.currentDestination

    InventoryNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                item(
                    selected = selected,
                    onClick = { appState.navigateToTopLevelDestination(destination) },
                    icon = {
                        Icon(imageVector = destination.unselectedIcon, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = destination.selectedIcon, contentDescription = null)
                    },
                    label = { Text(stringResource(destination.iconTextId)) },
                    modifier = Modifier.testTag("InventoryNavItem").then(Modifier),
                )
            }
        },
        windowAdaptiveInfo = windowAdaptiveInfo,
    ) {
        Scaffold(
            modifier = modifier,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { padding ->
            Column(
                Modifier.fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                    )
            ) {
                DialogHost(dialogManager = appState.dialogManager, paddingValues = padding)
                InventoryNavHost(appState = appState)
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false
