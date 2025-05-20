package io.siffert.mobile.app.core.common.dialog

import androidx.compose.runtime.Composable
import io.siffert.mobile.app.core.common.dialog.dialogs.AlertDialog

sealed interface AppDialog {
    val dismissibleByUser: Boolean
        get() = true

    @Composable fun Dialog(dismiss: () -> Unit)

    data object Test : AppDialog {
        @Composable
        override fun Dialog(dismiss: () -> Unit) {
            AlertDialog(title = "Test", message = "Long test message ")
        }
    }
}
