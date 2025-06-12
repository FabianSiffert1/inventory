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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.siffert.mobile.app.core.common.dialog.handling.DialogHost
import io.siffert.mobile.app.feature.assets.navigation.AssetsRoute
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryBackground
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryGradientBackground
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryNavigationSuiteScaffold
import io.siffert.mobile.app.inventory.core.designsystem.theme.LocalGradientColors
import io.siffert.mobile.app.inventory.navigation.InventoryAppNavigator
import io.siffert.mobile.app.inventory.navigation.TopLevelDestination

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
  val topLevelBackStack = remember { TopLevelBackStack<NavKey>(AssetsRoute) }
  val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

  InventoryNavigationSuiteScaffold(
      navigationSuiteItems = {
        topLevelDestinations.forEach { destination ->
          val isSelected = destination.route == topLevelBackStack.topLevelKey
          item(
              selected = isSelected,
              onClick = { topLevelBackStack.addTopLevel(destination.route) },
              icon = { Icon(imageVector = destination.unselectedIcon, contentDescription = null) },
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
              .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))) {
            DialogHost(dialogManager = appState.dialogManager, paddingValues = padding)
            InventoryAppNavigator(topLevelBackStack = topLevelBackStack)
          }
    }
  }
}
