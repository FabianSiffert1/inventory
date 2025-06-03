package io.siffert.mobile.app.inventory.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import io.siffert.mobile.app.feature.assets.navigation.AssetsBaseRoute
import io.siffert.mobile.app.feature.assets.navigation.Nav3AssetsBaseRoute
import io.siffert.mobile.app.feature.assets.navigation.assetsSection
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetDetails
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetEditor
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetSearch
import io.siffert.mobile.app.inventory.feature.balance.navigation.Nav3BalanceBaseRoute
import io.siffert.mobile.app.inventory.feature.balance.navigation.balanceSection
import io.siffert.mobile.app.inventory.ui.InventoryAppState

@Composable
fun InventoryNavHost(appState: InventoryAppState, modifier: Modifier = Modifier) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        // todo: rollback to BalanceBaseRoute
        startDestination = AssetsBaseRoute,
        modifier = modifier,
    ) {
        balanceSection()
        assetsSection(
            onAssetClick = navController::navigateToAssetDetails,
            onBackClick = navController::popBackStack,
            onNavigateToAssetEditorClick = navController::navigateToAssetEditor,
            onSearchClick = navController::navigateToAssetSearch,
        )
    }
}

@Composable
fun InventoryAppNavigator(appState: InventoryAppState, modifier: Modifier = Modifier) {
    // replace AssetsBaseRoute with HomeScreen/BalanceScreen

    val backStack = remember { mutableStateListOf<Any>(Nav3AssetsBaseRoute) }
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Nav3AssetsBaseRoute ->
                    NavEntry(key) {
                        // assetsNavEntry()
                        TestScreen(onClick = { backStack.add(Nav3BalanceBaseRoute("assetId1")) })
                    }
                is Nav3BalanceBaseRoute ->
                    NavEntry(key) {
                        SecondTestScreen(
                            itemId = key.id,
                            onClick = { backStack.removeLastOrNull() },
                        )
                    }
                else -> throw IllegalArgumentException("Unknown Screen")
            }
        },
    )
}

@Composable
fun TestScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = onClick) { Text("Navigate to 2nd Screen") }
    }
}

@Composable
fun SecondTestScreen(onClick: () -> Unit, modifier: Modifier = Modifier, itemId: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("itemId: $itemId")
        Button(onClick = onClick) { Text("Navigate to 1 Screen") }
    }
}
