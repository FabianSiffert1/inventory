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
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation.AssetCreationScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import kotlinx.serialization.Serializable

@Serializable data object AssetsBaseRoute

@Serializable data object AssetsRoute

@Serializable data class AssetDetailsRoute(val assetId: String)

@Serializable data object AssetCreationRoute

fun NavController.navigateToAssets(navOptions: NavOptions) =
    navigate(route = AssetsRoute, navOptions)

fun NavController.navigateToAssetDetails(
    assetId: String,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route = AssetDetailsRoute(assetId = assetId), navOptions)
}

fun NavController.navigateToAssetCreation(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AssetCreationRoute, navOptions)

// todo: replace placeholder
fun NavController.navigateToAssetSearch(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AssetsRoute, navOptions)

fun NavGraphBuilder.assetsSection(
    onAssetClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onCreateAssetClick: () -> Unit,
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
                onCreateAssetClick = onCreateAssetClick,
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
            AssetDetailsScreen(assetId = assetId, navigateBack = onBackClick)
        }
        composable<AssetCreationRoute>(
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
            AssetCreationScreen(navigateBack = onBackClick)
        }
    }
}
