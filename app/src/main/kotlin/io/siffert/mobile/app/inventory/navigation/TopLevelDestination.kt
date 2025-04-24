package io.siffert.mobile.app.inventory.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import io.siffert.mobile.app.feature.assets.navigation.AssetsBaseRoute
import io.siffert.mobile.app.feature.assets.navigation.AssetsRoute
import io.siffert.mobile.app.inventory.core.designsystem.icons.Chart
import io.siffert.mobile.app.inventory.core.designsystem.icons.PiggyBank
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.feature.balance.R as balanceResource
import io.siffert.mobile.app.feature.assets.R as assetsResource
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceBaseRoute
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
    val showTopAppBar: Boolean = false,
) {
    BALANCE(
        selectedIcon = Cozy.icon.Chart,
        unselectedIcon = Cozy.icon.Chart,
        iconTextId = balanceResource.string.feature_balance_title,
        titleTextId = balanceResource.string.feature_balance_title,
        route = BalanceRoute::class,
        baseRoute = BalanceBaseRoute::class,
    ),
    ASSETS(
        selectedIcon = Cozy.icon.PiggyBank,
        unselectedIcon = Cozy.icon.PiggyBank,
        iconTextId = assetsResource.string.feature_assets_title,
        titleTextId = assetsResource.string.feature_assets_title,
        route = AssetsRoute::class,
        baseRoute = AssetsBaseRoute::class,
        showTopAppBar = true,
    )
}