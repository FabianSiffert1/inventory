package io.siffert.mobile.app.feature.assets.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import io.siffert.mobile.app.feature.assets.AssetsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorMode
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreen
import kotlinx.serialization.Serializable

@Serializable data object AssetsBaseRoute

@Serializable data object AssetsRoute

@Serializable
data class AssetEditorRoute(val assetId: String? = null, val assetEditorMode: AssetEditorMode)

@Serializable data class AssetDetailsRoute(val assetId: String)

fun NavController.navigateToAssets(navOptions: NavOptions) =
    navigate(route = AssetsRoute, navOptions)

fun NavController.navigateToAssetDetails(
    assetId: String,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route = AssetDetailsRoute(assetId = assetId), navOptions)
}

fun NavController.navigateToAssetEditor(
    assetId: String? = null,
    assetEditorMode: AssetEditorMode,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) =
    navigate(
        route = AssetEditorRoute(assetId = assetId, assetEditorMode = assetEditorMode),
        navOptions,
    )

// todo: replace placeholder
fun NavController.navigateToAssetSearch(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AssetsRoute, navOptions)

fun NavGraphBuilder.assetsSection(
    onAssetClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onNavigateToAssetEditorClick: (String?, AssetEditorMode) -> Unit,
    onBackClick: () -> Unit,
) {
    navigation<AssetsBaseRoute>(startDestination = AssetsRoute) {
        composable<AssetsRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
        ) {
            AssetsScreen(
                onSearchClick = onSearchClick,
                onAssetClick = onAssetClick,
                onCreateAssetClick = { onNavigateToAssetEditorClick(null, AssetEditorMode.CREATE) },
            )
        }
        composable<AssetDetailsRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
        ) { asset ->
            val assetId = asset.toRoute<AssetDetailsRoute>().assetId
            AssetDetailsScreen(
                assetId = assetId,
                navigateBack = onBackClick,
                onEditAssetClick = { onNavigateToAssetEditorClick(assetId, AssetEditorMode.EDIT) },
            )
        }
        composable<AssetEditorRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
        ) { backStackEntry ->
            val assetId = backStackEntry.toRoute<AssetEditorRoute>().assetId
            val assetEditorMode = backStackEntry.toRoute<AssetEditorRoute>().assetEditorMode

            AssetEditorScreen(
                assetId = assetId,
                assetEditorMode = assetEditorMode,
                navigateBack = onBackClick,
            )
        }
    }
}
