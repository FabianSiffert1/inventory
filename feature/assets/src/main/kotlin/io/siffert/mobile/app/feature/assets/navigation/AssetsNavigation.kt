package io.siffert.mobile.app.feature.assets.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.ui.NavDisplay
import io.siffert.mobile.app.feature.assets.AssetsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorMode
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreen
import kotlinx.serialization.Serializable

@Serializable data object AssetsRoute : NavKey

@Serializable
data class AssetEditorRoute(val assetId: String? = null, val assetEditorMode: AssetEditorMode) :
    NavKey

@Serializable data class AssetDetailsRoute(val assetId: String) : NavKey

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

fun EntryProviderBuilder<NavKey>.assetsSection(
    onAssetClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onNavigateToAssetEditorClick: (String?, AssetEditorMode) -> Unit,
    onBackClick: () -> Unit,
) {
  val assetAnimations =
      NavDisplay.transitionSpec {
        slideInVertically(initialOffsetY = { it }, animationSpec = tween(1000)) togetherWith
            ExitTransition.KeepUntilTransitionsFinished
      } +
          NavDisplay.popTransitionSpec {
            EnterTransition.None togetherWith
                slideOutVertically(targetOffsetY = { it }, animationSpec = tween(1000))
          } +
          NavDisplay.predictivePopTransitionSpec {
            EnterTransition.None togetherWith
                slideOutVertically(targetOffsetY = { it }, animationSpec = tween(1000))
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
    )
  }
  entry<AssetEditorRoute>(metadata = assetAnimations) { backStackEntry ->
    AssetEditorScreen(
        assetId = backStackEntry.assetId,
        assetEditorMode = backStackEntry.assetEditorMode,
        navigateBack = onBackClick,
    )
  }
}
