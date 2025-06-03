package io.siffert.mobile.app.inventory.feature.balance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation3.runtime.NavEntry
import io.siffert.mobile.app.inventory.feature.balance.BalanceScreen
import kotlinx.serialization.Serializable

@Serializable
data object BalanceRoute // route to Balance screen

data class Nav3BalanceBaseRoute(val id: String)

@Serializable
data object BalanceBaseRoute // route to base navigation graph

fun NavController.navigateToBalance(navOptions: NavOptions) =
    navigate(route = BalanceRoute, navOptions)

fun NavGraphBuilder.balanceSection() {
    navigation<BalanceBaseRoute>(startDestination = BalanceRoute) {
        composable<BalanceRoute>(
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right
                )
            },
            exitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left
                )
            },
        ) {
            BalanceScreen()
        }
    }
}

fun balanceNavEntry(): NavEntry<Any> =
    NavEntry(Nav3BalanceBaseRoute("assetId1")) { BalanceScreen() }
