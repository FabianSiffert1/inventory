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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import io.siffert.mobile.app.feature.assets.R as assetsResource
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryBackground
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryGradientBackground
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryNavigationSuiteScaffold
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryTopAppBar
import io.siffert.mobile.app.inventory.core.designsystem.icons.AddCircle
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
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
            InventoryApp(
                appState = appState,
                windowAdaptiveInfo = windowAdaptiveInfo,
                onTopAppBarActionClick = {},
                onTopAppBarNavigationClick = { appState.navigateToSearch() },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InventoryApp(
    appState: InventoryAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    onTopAppBarActionClick: () -> Unit = {},
    onTopAppBarNavigationClick: () -> Unit = {},
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
                val currentTopLevelDestination = appState.currentTopLevelDestination
                if (currentTopLevelDestination?.showTopAppBar == true) {
                    InventoryTopAppBar(
                        navigationIcon = Icons.Filled.Search,
                        navigationIconContentDescription =
                            stringResource(
                                id = assetsResource.string.feature_assets_top_app_bar_search
                            ),
                        actionIcon = Cozy.icon.AddCircle,
                        actionIconContentDescription =
                            stringResource(
                                id = assetsResource.string.feature_assets_top_app_bar_add_asset
                            ),
                        colors =
                            TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                        onNavigationClick = { onTopAppBarNavigationClick() },
                        onActionClick = { onTopAppBarActionClick() },
                    )
                }
                InventoryNavHost(appState = appState)
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false
