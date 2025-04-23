package io.siffert.mobile.app.feature.assets.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.siffert.mobile.app.feature.assets.AssetsScreen
import kotlinx.serialization.Serializable


@Serializable
data object AssetsRoute // route to Assets screen

@Serializable
data object AssetsBaseRoute //route to Assets base navigation graph


fun NavController.navigateToAssets(navOptions: NavOptions) = navigate(route = AssetsRoute, navOptions)


fun NavGraphBuilder.assetsSection(
){
    navigation<AssetsBaseRoute>(startDestination = AssetsRoute){
        composable<AssetsRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                )
            }
        ) {
            AssetsScreen()
        }
    }
}
