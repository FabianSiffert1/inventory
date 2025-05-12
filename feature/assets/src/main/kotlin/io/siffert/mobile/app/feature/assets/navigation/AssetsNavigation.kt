package io.siffert.mobile.app.feature.assets.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import io.siffert.mobile.app.feature.assets.AssetsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import kotlinx.serialization.Serializable

@Serializable data object AssetsRoute

@Serializable data class AssetDetailsRoute(val assetId: String)

@Serializable data object AssetsBaseRoute

fun NavController.navigateToAssets(navOptions: NavOptions) =
    navigate(route = AssetsRoute, navOptions)

fun NavController.navigateToAssetDetails(
    assetId: String,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route = AssetDetailsRoute(assetId = assetId), navOptions)
}

// todo: fix animation when entering asset
fun NavGraphBuilder.assetsSection(onAssetClick: (String) -> Unit, onBackClick: () -> Unit) {
    navigation<AssetsBaseRoute>(startDestination = AssetsRoute) {
        composable<AssetsRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
            exitTransition = {
                val isTargetInAssetGraph =
                    targetState.destination.hierarchy.any {
                        it.route == AssetsBaseRoute::class.simpleName
                    }
                if (!isTargetInAssetGraph)
                    return@composable fadeOut(animationSpec = tween(durationMillis = 0))
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
        ) {
            AssetsScreen(onAssetClick = onAssetClick)
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
            AssetDetailsScreen(assetId = assetId, onBackClick = onBackClick)
        }
    }
}
