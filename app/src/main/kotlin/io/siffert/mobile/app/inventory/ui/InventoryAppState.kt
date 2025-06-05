package io.siffert.mobile.app.inventory.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import io.siffert.mobile.app.core.common.dialog.handling.DialogManager
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

    private var topLevelStacks : LinkedHashMap<T, SnapshotStateList<T>> = linkedMapOf(
        startKey to mutableStateListOf(startKey)
    )

    var topLevelKey by mutableStateOf(startKey)
        private set

    val backStack = mutableStateListOf(startKey)

    private fun updateBackStack() =
        backStack.apply {
            clear()
            addAll(topLevelStacks.flatMap { it.value })
        }

    fun addTopLevel(key: T){

        if (topLevelStacks[key] == null){
            topLevelStacks[key] = mutableStateListOf(key)
        } else {
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
        topLevelStacks.remove(removedKey)
        topLevelKey = topLevelStacks.keys.last()
        updateBackStack()
    }
}


