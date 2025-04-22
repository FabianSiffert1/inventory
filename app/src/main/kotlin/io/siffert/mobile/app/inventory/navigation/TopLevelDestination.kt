package io.siffert.mobile.app.inventory.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import io.siffert.mobile.app.feature.balance.R as balanceResource
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceBaseRoute
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route
) {
    BALANCE(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = balanceResource.string.feature_balance_title,
        titleTextId = balanceResource.string.feature_balance_title,
        route = BalanceRoute::class,
        baseRoute = BalanceBaseRoute::class,
    )

}