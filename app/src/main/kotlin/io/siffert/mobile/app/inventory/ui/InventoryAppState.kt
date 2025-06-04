package io.siffert.mobile.app.inventory.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.util.trace
import androidx.navigation3.runtime.NavBackStack
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
import io.siffert.mobile.app.feature.assets.navigation.navigateToAssets
import io.siffert.mobile.app.inventory.feature.balance.navigation.navigateToBalance
import io.siffert.mobile.app.inventory.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject

@Composable
fun rememberInventoryAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    dialogManager: DialogManager = koinInject(),
): InventoryAppState {
    return remember( coroutineScope) {
        InventoryAppState( dialogManager = dialogManager)
    }
}

@Stable
class InventoryAppState(val dialogManager: DialogManager)


class TopLevelBackStack<T: Any>(startKey: T) {

    // Maintain a stack for each top level route
    private var topLevelStacks : LinkedHashMap<T, SnapshotStateList<T>> = linkedMapOf(
        startKey to mutableStateListOf(startKey)
    )

    // Expose the current top level route for consumers
    var topLevelKey by mutableStateOf(startKey)
        private set

    // Expose the back stack so it can be rendered by the NavDisplay
    val backStack = mutableStateListOf(startKey)

    private fun updateBackStack() =
        backStack.apply {
            clear()
            addAll(topLevelStacks.flatMap { it.value })
        }

    fun addTopLevel(key: T){

        // If the top level doesn't exist, add it
        if (topLevelStacks[key] == null){
            topLevelStacks.put(key, mutableStateListOf(key))
        } else {
            // Otherwise just move it to the end of the stacks
            topLevelStacks.apply {
                remove(key)?.let {
                    put(key, it)
                }
            }
        }
        topLevelKey = key
        updateBackStack()
    }

    fun add(key: T){
        topLevelStacks[topLevelKey]?.add(key)
        updateBackStack()
    }

    fun removeLast(){
        val removedKey = topLevelStacks[topLevelKey]?.removeLastOrNull()
        // If the removed key was a top level key, remove the associated top level stack
        topLevelStacks.remove(removedKey)
        topLevelKey = topLevelStacks.keys.last()
        updateBackStack()
    }
}


