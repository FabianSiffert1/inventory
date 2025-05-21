package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
internal fun <T : Enum<T>> AssetBottomSheetListItem(
    toggleBottomSheet: () -> Unit,
    enumEntries: Array<T>,
    label: String,
    currentlySelectedItemName: String,
    showBottomSheet: Boolean,
    onItemClick: (T) -> Unit,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    ListItem(
        modifier =
            Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(8.dp)).clickable {
                toggleBottomSheet()
            },
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
        headlineContent = {
            Column {
                Text(
                    text = label,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = currentlySelectedItemName)
                    if (trailingContent != null) {
                        trailingContent()
                    }
                }
            }
        },
    )
    if (showBottomSheet) {
        AssetBottomSheet(
            values = enumEntries,
            sheetState = bottomSheetState,
            onItemSelected = onItemClick,
            onDismiss = toggleBottomSheet,
        )
    }
}
