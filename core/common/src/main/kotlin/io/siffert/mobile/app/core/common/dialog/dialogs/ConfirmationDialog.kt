package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.core.common.R
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmationButtonText: String? = null,
    confirmationButtonTextColor: Color = MaterialTheme.colorScheme.onSurface,
    dismissButtonText: String? = null,
) =
    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
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

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(
                modifier =
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(8.dp),
                    ),
                onClick = onDismiss,
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    text = dismissButtonText ?: stringResource(id = R.string.dialogs_cancel),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(
                modifier =
                    Modifier.border(
                        width = 1.dp,
                        color = confirmationButtonTextColor,
                        shape = RoundedCornerShape(8.dp),
                    ),
                onClick = onConfirm,
            ) {
                Text(
                    text = confirmationButtonText ?: stringResource(id = R.string.dialogs_confirm),
                    style = MaterialTheme.typography.titleMedium,
                    color = confirmationButtonTextColor,
                )
            }
        }
    }

@Composable
@PreviewLightDark
private fun Preview() = InventoryTheme {
    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.onSurface).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ConfirmationDialog(
            title = "Title",
            message = "This dialog represents a decision the user has to make",
            onDismiss = {},
            onConfirm = {},
            confirmationButtonTextColor = Color.Red,
        )
    }
}
