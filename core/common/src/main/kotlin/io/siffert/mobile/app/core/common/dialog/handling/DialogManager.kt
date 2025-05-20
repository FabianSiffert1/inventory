package io.siffert.mobile.app.core.common.dialog.handling

import androidx.compose.runtime.Stable
import io.siffert.mobile.app.core.common.dialog.AppDialog
import kotlinx.coroutines.flow.Flow

@Stable
interface DialogManager {
    val currentDialog: Flow<AppDialog?>

    /**
     * Enqueues a dialog to be displayed.
     *
     * @param displayImmediately If set to true, the dialog will be inserted at the beginning of the
     *   dialog queue, which means the current dialog is going to be hidden (if there is any) and
     *   the new dialog will be displayed immediately. After the new dialog was dismissed, the old
     *   dialog is going to be displayed again.
     */
    fun enqueue(dialog: AppDialog, displayImmediately: Boolean = false)

    fun onDialogDismissed(dialog: AppDialog)
}
