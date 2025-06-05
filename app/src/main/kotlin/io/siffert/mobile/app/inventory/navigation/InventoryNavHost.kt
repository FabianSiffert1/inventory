package io.siffert.mobile.app.inventory.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import io.siffert.mobile.app.feature.assets.navigation.assetsSection
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetDetails
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetEditor
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetSearch
import io.siffert.mobile.app.inventory.feature.balance.navigation.balanceSection

@Composable
fun InventoryAppNavigator(backStack: SnapshotStateList<NavKey>, modifier: Modifier = Modifier) {

  NavDisplay(
      modifier = modifier,
      backStack = backStack,
      onBack = { backStack.removeLastOrNull() },
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
