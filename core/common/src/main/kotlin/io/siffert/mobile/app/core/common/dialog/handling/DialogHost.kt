package io.siffert.mobile.app.core.common.dialog.handling

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DialogHost(
    dialogManager: DialogManager,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val currentDialog = dialogManager.currentDialog.collectAsStateWithLifecycle(null).value

    if (currentDialog != null) {
        Dialog(
            onDismissRequest = {
                if (currentDialog.dismissibleByUser) {
                    dialogManager.onDialogDismissed(currentDialog)
                }
            },
            content = {
                Box(modifier = Modifier.padding(paddingValues)) {
                    currentDialog.Dialog(
                        dismiss = { dialogManager.onDialogDismissed(currentDialog) }
                    )
                }
            },
        )
    }
}
