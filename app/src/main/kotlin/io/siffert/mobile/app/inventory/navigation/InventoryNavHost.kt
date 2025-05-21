package io.siffert.mobile.app.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import io.siffert.mobile.app.feature.assets.navigation.AssetsBaseRoute
import io.siffert.mobile.app.feature.assets.navigation.assetsSection
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetCreation
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetDetails
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetSearch
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
            onCreateAssetClick = navController::navigateToAssetCreation,
            onSearchClick = navController::navigateToAssetSearch,
        )
    }
}
