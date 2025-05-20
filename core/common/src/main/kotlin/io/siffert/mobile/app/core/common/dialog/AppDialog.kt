package io.siffert.mobile.app.core.common.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.siffert.mobile.app.core.common.R
import io.siffert.mobile.app.core.common.dialog.dialogs.ConfirmationDialog
import io.siffert.mobile.app.core.common.dialog.dialogs.InformationDialog

sealed interface AppDialog {
    val dismissibleByUser: Boolean
        get() = true

    @Composable fun Dialog(onDismiss: () -> Unit)

    data object InformationDialog : AppDialog {
        @Composable
        override fun Dialog(onDismiss: () -> Unit) {
            InformationDialog(
                title = "Information",
                message = "Long, informative message",
                dismiss = onDismiss,
            )
        }
    }

    data class DeleteAssetDialog(val onConfirm: () -> Unit) : AppDialog {
        @Composable
        override fun Dialog(onDismiss: () -> Unit) {
            ConfirmationDialog(
                title = stringResource(id = R.string.dialogs_asset_deletion_title),
                message = stringResource(id = R.string.dialogs_asset_deletion_subtitle),
                onConfirm = {
                    onConfirm()
                    onDismiss()
                },
                onDismiss = onDismiss,
                confirmationButtonText =
                    stringResource(id = R.string.dialogs_asset_deletion_button_confirm),
            )
        }
    }
}
