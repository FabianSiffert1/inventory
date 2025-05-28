package io.siffert.mobile.app.core.common.dialog.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun InformationDialog(title: String, dismiss: () -> Unit, modifier: Modifier = Modifier) =
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

        TextButton(
            modifier =
                Modifier.align(Alignment.End)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(8.dp),
                    ),
            onClick = dismiss,
        ) {
            Text(
                text = stringResource(id = R.string.dialogs_confirm),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

@Composable
fun InformationDialog(
    title: String,
    message: String,
    dismiss: () -> Unit,
    modifier: Modifier = Modifier,
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

        TextButton(
            modifier =
                Modifier.align(Alignment.End)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(8.dp),
                    ),
            onClick = dismiss,
        ) {
            Text(
                text = stringResource(R.string.dialogs_confirm),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

@Composable
@PreviewLightDark
private fun Preview() = InventoryTheme {
    Column(
        modifier =
            Modifier.background(color = MaterialTheme.colorScheme.surfaceVariant).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        InformationDialog(title = "This Dialog doesnt have a message", dismiss = {})
        InformationDialog(
            title = "Title",
            message = "This is an important message the user has to acknowledge!",
            dismiss = {},
        )
    }
}
