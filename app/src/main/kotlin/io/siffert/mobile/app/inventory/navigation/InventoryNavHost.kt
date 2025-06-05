package io.siffert.mobile.app.inventory.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import io.siffert.mobile.app.feature.assets.navigation.AssetsRoute
import io.siffert.mobile.app.feature.assets.navigation.assetsSection
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetDetails
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetEditor
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetSearch
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceTopLevelRoute
import io.siffert.mobile.app.inventory.feature.balance.navigation.balanceSection
import io.siffert.mobile.app.inventory.ui.TopLevelBackStack

@Composable
fun InventoryAppNavigator(
    modifier: Modifier = Modifier,
    topLevelBackStack: TopLevelBackStack<NavKey>
) {
    val backStack = topLevelBackStack.backStack

  NavDisplay(
      modifier = modifier,
      backStack = backStack,
      onBack = { backStack.removeLastOrNull() },
      transitionSpec = {
          slideInHorizontally(initialOffsetX = { it }) togetherWith
                  slideOutHorizontally(targetOffsetX = { -it })
      },
      popTransitionSpec = {
          slideInHorizontally(initialOffsetX = { -it }) togetherWith
                  slideOutHorizontally(targetOffsetX = { it })
      },
      predictivePopTransitionSpec = {
          slideInHorizontally(initialOffsetX = { -it }) togetherWith
                  slideOutHorizontally(targetOffsetX = { it })
      },
      entryDecorators =
          listOf(
              rememberSceneSetupNavEntryDecorator(),
              rememberSavedStateNavEntryDecorator(),
              rememberViewModelStoreNavEntryDecorator(),
          ),
      entryProvider =
          entryProvider {
            assetsSection(
                onAssetClick = { key ->
                    backStack.navigateToAssetDetails(key)},
                onSearchClick = { backStack.navigateToAssetSearch()},
                onNavigateToAssetEditorClick = { assetId, assetEditorMode ->
                    backStack.navigateToAssetEditor(assetId = assetId, assetEditorMode = assetEditorMode)
                },
                onBackClick = {backStack.removeLastOrNull()},
            )
            balanceSection()
          },
  )
}
