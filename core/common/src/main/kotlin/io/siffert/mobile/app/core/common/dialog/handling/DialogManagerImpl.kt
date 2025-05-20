package io.siffert.mobile.app.core.common.dialog.handling

import android.util.Log
import io.siffert.mobile.app.core.common.dialog.AppDialog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class DialogManagerImpl : DialogManager {
    private val dialogQueue = MutableStateFlow(ArrayDeque<AppDialog>())

    override val currentDialog: Flow<AppDialog?> =
        dialogQueue.asStateFlow().map { it.firstOrNull() }

    init {
        Log.d("DialogManager", "DialogManager initialized")
    }

    override fun enqueue(dialog: AppDialog, displayImmediately: Boolean) {
        Log.d("DialogManager", "Enqueue new dialog: $dialog")
        dialogQueue.update { currentState ->
            Log.d("DialogManager", "current state: ${currentState.toList()}")

            if (currentState.contains(dialog)) {
                Log.d(
                    "DialogManager",
                    "Dialog $dialog is already queued. Ignoring enqueue request.",
                )
                return
            }

            ArrayDeque(currentState.toList())
                .apply {
                    when (displayImmediately) {
                        true -> addFirst(dialog)
                        false -> addLast(dialog)
                    }
                }
                .also { Log.d("DialogManager", "New state: ${it.toList()}") }
        }
    }

    override fun onDialogDismissed(dialog: AppDialog) =
        dialogQueue.update { currentState ->
            ArrayDeque(currentState.toList())
                .apply { remove(dialog) }
                .also {
                    Log.d("DialogManager", "Dialog removed from queue: $dialog")
                    Log.d("DialogManager", "New dialog queue state is: $it")
                }
        }
}
