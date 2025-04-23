package io.siffert.mobile.app.inventory.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssets
import io.siffert.mobile.app.inventory.feature.balance.navigation.navigateToBalance
import io.siffert.mobile.app.inventory.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberInventoryAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): InventoryAppState {
    return remember(navController, coroutineScope) {
        InventoryAppState(navController = navController, coroutineScope = coroutineScope)
    }
}


@Stable
class InventoryAppState(val navController: NavHostController, val coroutineScope: CoroutineScope) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            // Collect the currentBackStackEntryFlow as a state
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }


            when (topLevelDestination) {
                TopLevelDestination.BALANCE -> navController.navigateToBalance(topLevelNavOptions)
                TopLevelDestination.ASSETS -> navController.navigateToAssets(topLevelNavOptions)
            }


        }
    }
}