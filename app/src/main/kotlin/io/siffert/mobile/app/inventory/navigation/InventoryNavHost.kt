package io.siffert.mobile.app.inventory.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import io.siffert.mobile.app.feature.assets.AssetsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.AssetDetailsScreen
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorMode
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor.AssetEditorScreen
import io.siffert.mobile.app.feature.assets.navigation.AssetDetailsRoute
import io.siffert.mobile.app.feature.assets.navigation.AssetEditorRoute
import io.siffert.mobile.app.feature.assets.navigation.AssetsBaseRoute
import io.siffert.mobile.app.feature.assets.navigation.AssetsRoute
import io.siffert.mobile.app.feature.assets.navigation.assetsSection
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetDetails
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetEditor
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssetSearch
import io.siffert.mobile.app.inventory.feature.balance.BalanceScreen
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceBaseRoute
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceRoute
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

    val backStack  = rememberNavBackStack(AssetsRoute)


    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        transitionSpec = {
            slideInHorizontally(animationSpec = tween(300)) + fadeIn() togetherWith
                    scaleOut(targetScale = .9f, animationSpec = tween(300)) + fadeOut()
        },
        popTransitionSpec = {
            slideInHorizontally(animationSpec = tween(300)) + fadeIn() togetherWith
                    slideOutHorizontally(animationSpec = tween(300)) + fadeOut()
        },
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider =  entryProvider {
            entry<AssetsRoute> {
                AssetsScreen(
                    onAssetClick ={
                        assetId ->
                        backStack.add(AssetDetailsRoute(
                        assetId = assetId
                    ))},
                    onCreateAssetClick = {
                        backStack.add(AssetEditorRoute(assetEditorMode = AssetEditorMode.CREATE))
                    },
                    onSearchClick = {backStack.add(BalanceRoute)},
                )
            }
            entry<AssetDetailsRoute> {
                key ->
                AssetDetailsScreen(
                    assetId = key.assetId,
                    navigateBack = {backStack.removeLastOrNull() },
                    onEditAssetClick = {
                        backStack.add(AssetEditorRoute(assetId = key.assetId, assetEditorMode = AssetEditorMode.EDIT))}
                )
            }
            entry<AssetEditorRoute> {
                key ->
                AssetEditorScreen(
                    assetId = key.assetId,
                    assetEditorMode = key.assetEditorMode,
                    navigateBack = {backStack.removeLastOrNull()}
                )
            }
            entry<BalanceRoute> {
                BalanceScreen()
            }
        },
    )
}
