package io.siffert.mobile.app.inventory.feature.balance.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable



@Serializable data object BalanceRoute // route to Balance screen

@Serializable data object BalanceBaseRoute //route to base navigation graph


fun NavController.navigateToBalance(navOptions: NavOptions) = navigate(route = BalanceRoute, navOptions)


fun NavGraphBuilder.balanceSection(
){
    navigation<BalanceBaseRoute>(startDestination = BalanceRoute){
        composable<BalanceRoute>() {

        }
    }
}
