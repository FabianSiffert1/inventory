package io.siffert.mobile.app.feature.assets.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.ui.NavDisplay
import io.siffert.mobile.app.feature.assets.AssetsRoute
import io.siffert.mobile.app.feature.assets.AssetsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsRoute
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceOverview.PriceOverviewRoute
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceOverview.PriceOverviewScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorMode
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorRoute
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreen

fun NavBackStack.navigateToAssets() = add(AssetsRoute)

fun NavBackStack.navigateToAssetDetails(
    assetId: String,
) {
  add(AssetDetailsRoute(assetId = assetId))
}

fun NavBackStack.navigateToAssetEditor(
    assetId: String? = null,
    assetEditorMode: AssetEditorMode,
) =
    add(
        AssetEditorRoute(assetId = assetId, assetEditorMode = assetEditorMode),
    )

fun NavBackStack.navigateToAssetSearch() = add(AssetsRoute)

fun NavBackStack.navigateToAssetPriceOverview(
    assetId: String,
) = add(PriceOverviewRoute(assetId = assetId))

fun EntryProviderBuilder<NavKey>.assetsSection(
    onAssetClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onNavigateToAssetEditorClick: (String?, AssetEditorMode) -> Unit,
    onNavigateToPriceOverviewClick: (String) -> Unit,
    onBackClick: () -> Unit,
) {
  val assetAnimations =
      NavDisplay.transitionSpec {
        slideInHorizontally(initialOffsetX = { it }) togetherWith
            slideOutHorizontally(targetOffsetX = { -it })
      } +
          NavDisplay.popTransitionSpec {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
          } +
          NavDisplay.predictivePopTransitionSpec {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
          }
  entry<AssetsRoute>(metadata = assetAnimations) {
    AssetsScreen(
        onSearchClick = onSearchClick,
        onAssetClick = onAssetClick,
        onCreateAssetClick = { onNavigateToAssetEditorClick(null, AssetEditorMode.CREATE) },
    )
  }
  entry<AssetDetailsRoute>(metadata = assetAnimations) { backStackEntry ->
    AssetDetailsScreen(
        assetId = backStackEntry.assetId,
        navigateBack = onBackClick,
        onEditAssetClick = {
          onNavigateToAssetEditorClick(backStackEntry.assetId, AssetEditorMode.EDIT)
        },
        onNavigateToPriceOverviewClick = { onNavigateToPriceOverviewClick(backStackEntry.assetId) })
  }
  entry<AssetEditorRoute>(metadata = assetAnimations) { backStackEntry ->
    AssetEditorScreen(
        assetId = backStackEntry.assetId,
        assetEditorMode = backStackEntry.assetEditorMode,
        navigateBack = onBackClick,
    )
  }
  entry<PriceOverviewRoute>(metadata = assetAnimations) { backStackEntry ->
    PriceOverviewScreen(
        assetId = backStackEntry.assetId,
        navigateBack = onBackClick,
    )
  }
}
