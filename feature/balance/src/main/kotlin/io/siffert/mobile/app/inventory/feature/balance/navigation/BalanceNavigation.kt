package io.siffert.mobile.app.inventory.feature.balance.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import io.siffert.mobile.app.inventory.feature.balance.BalanceScreen
import kotlinx.serialization.Serializable

@Serializable
data object BalanceRoute : NavKey

fun NavBackStack.navigateToBalance() =
    add(BalanceRoute)

fun EntryProviderBuilder<NavKey>.balanceSection() {
        entry<BalanceRoute>{
            BalanceScreen()
        }
}
