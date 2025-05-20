package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.core.common.R
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme

@Composable
fun AlertDialog(title: String, modifier: Modifier = Modifier, dismiss: () -> Unit = {}) =
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = dismiss,
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.dialogs_accept),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

@Composable
fun AlertDialog(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    dismiss: () -> Unit = {},
) =
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = dismiss,
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(R.string.dialogs_accept),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

@Composable
@PreviewLightDark
private fun AlertDialogPreview() = InventoryTheme {
    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AlertDialog(
            title = "This is an important message the user has to acknowledge!",
            dismiss = {},
        )
        AlertDialog(
            title = "Title",
            message = "This is an important message the user has to acknowledge!",
            dismiss = {},
        )
    }
}
