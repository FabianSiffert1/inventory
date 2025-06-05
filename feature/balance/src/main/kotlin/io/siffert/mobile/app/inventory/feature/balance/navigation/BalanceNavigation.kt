package io.siffert.mobile.app.inventory.feature.balance.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import io.siffert.mobile.app.inventory.feature.balance.BalanceScreen
import kotlinx.serialization.Serializable

@Serializable
data object BalanceRoute : NavKey

@Serializable
data object BalanceTopLevelRoute : NavKey

@Serializable
data object BalanceSubScreen : NavKey

fun NavBackStack.navigateToBalance() =
    add(BalanceRoute)

fun EntryProviderBuilder<NavKey>.balanceSection() {
        entry<BalanceTopLevelRoute>{
            BalanceSection()
        }
}

@Composable
internal fun BalanceSection(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack (BalanceRoute)
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
        entryDecorators =
            listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<BalanceRoute>{
                    BalanceScreen(onNavigateToBalanceSubScreen = {
                        backStack.add(BalanceSubScreen)
                    })
                }
                entry<BalanceSubScreen>{
                    BalanceSubScreen(onNavigateBack = {backStack.removeLastOrNull()})
                }
            },
    )
}

@Composable
internal fun BalanceSubScreen(modifier: Modifier = Modifier, onNavigateBack : () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("BalanceSubScreen")
        Button(onClick = onNavigateBack) {
            Text("Navigate to Balance Overview")
        }
    }

}