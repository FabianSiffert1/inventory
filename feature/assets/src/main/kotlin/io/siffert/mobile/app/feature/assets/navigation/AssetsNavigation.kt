package io.siffert.mobile.app.feature.assets.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import io.siffert.mobile.app.feature.assets.AssetsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorMode
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreen
import kotlinx.serialization.Serializable

@Serializable data object AssetsRoute :NavKey

@Serializable
data class AssetEditorRoute(val assetId: String? = null, val assetEditorMode: AssetEditorMode) : NavKey

@Serializable data class AssetDetailsRoute(val assetId: String) : NavKey

fun NavBackStack.navigateToAssets() =
    add(AssetsRoute)

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

fun NavBackStack.navigateToAssetSearch() =
    add( AssetsRoute)

fun EntryProviderBuilder<NavKey>.assetsSection(
    onAssetClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onNavigateToAssetEditorClick: (String?, AssetEditorMode) -> Unit,
    onBackClick: () -> Unit,
) {
        entry<AssetsRoute>{
            AssetsScreen(
                onSearchClick = onSearchClick,
                onAssetClick = onAssetClick,
                onCreateAssetClick = { onNavigateToAssetEditorClick(null, AssetEditorMode.CREATE) },
            )
        }
        entry<AssetDetailsRoute>{ backStackEntry ->
            AssetDetailsScreen(
                assetId = backStackEntry.assetId,
                navigateBack = onBackClick,
                onEditAssetClick = { onNavigateToAssetEditorClick(backStackEntry.assetId, AssetEditorMode.EDIT) },
            )
        }
        entry<AssetEditorRoute>{ backStackEntry ->

            AssetEditorScreen(
                assetId = backStackEntry.assetId,
                assetEditorMode = backStackEntry.assetEditorMode,
                navigateBack = onBackClick,
            )
        }
    }
