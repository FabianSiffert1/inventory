package io.siffert.mobile.app.inventory.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import io.siffert.mobile.app.feature.assets.AssetsRoute
import io.siffert.mobile.app.feature.assets.R as assetsResource
import io.siffert.mobile.app.feature.balance.R as balanceResource
import io.siffert.mobile.app.inventory.core.designsystem.icons.Chart
import io.siffert.mobile.app.inventory.core.designsystem.icons.PiggyBank
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.inventory.feature.balance.navigation.BalanceTopLevelRoute

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: NavKey,
    val showTopAppBar: Boolean = false,
) {
  BALANCE(
      selectedIcon = Cozy.icon.Chart,
      unselectedIcon = Cozy.icon.Chart,
      iconTextId = balanceResource.string.feature_balance_title,
      titleTextId = balanceResource.string.feature_balance_title,
      route = BalanceTopLevelRoute,
  ),
  ASSETS(
      selectedIcon = Cozy.icon.PiggyBank,
      unselectedIcon = Cozy.icon.PiggyBank,
      iconTextId = assetsResource.string.feature_assets_title,
      titleTextId = assetsResource.string.feature_assets_title,
      route = AssetsRoute,
      showTopAppBar = true,
  )
}
