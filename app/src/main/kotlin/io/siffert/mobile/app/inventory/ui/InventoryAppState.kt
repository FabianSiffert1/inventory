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
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssets
import io.siffert.mobile.app.inventory.feature.balance.navigation.navigateToBalance
import io.siffert.mobile.app.inventory.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject

@Composable
fun rememberInventoryAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    dialogManager: DialogManager = koinInject(),
): InventoryAppState {
    return remember(navController, coroutineScope) {
        InventoryAppState(navController = navController, dialogManager = dialogManager)
    }
}

@Stable
class InventoryAppState(val navController: NavHostController, val dialogManager: DialogManager) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable
        get() {
            val currentEntry =
                navController.currentBackStackEntryFlow.collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    private val currentTopLevelDestination: TopLevelDestination?
        get() =
            TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                navController.currentDestination?.hasRoute(topLevelDestination.route) == true
            }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        if (topLevelDestination.route == currentTopLevelDestination?.route) {
            return
        }

        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
