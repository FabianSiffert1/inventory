package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun <T : Enum<T>> AssetDropdownMenu(
    values: Array<T>,
    assetLabel: String,
    currentlySelected: String,
    onItemSelected: (T) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        ListItem(
            modifier =
                Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(8.dp)).clickable {
                    expanded = !expanded
                },
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
            headlineContent = {
                Column {
                    Text(
                        text = assetLabel,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = currentlySelected)
                        if (trailingIcon != null) {
                            trailingIcon()
                        }
                    }
                }
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            containerColor = Color.Transparent,
            shadowElevation = 0.dp,
        ) {
            values.forEach { item ->
                DropdownMenuItem(
                    modifier =
                        Modifier.fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp),
                            )
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                shape = RoundedCornerShape(8.dp),
                            ),
                    text = {
                        Text(
                            text =
                                when (item) {
                                    is AssetClassWithStringRes -> stringResource(item.nameResource)
                                    else -> item.name
                                }
                        )
                    },
                    onClick = {
                        expanded = false
                        onItemSelected(item)
                    },
                )
                if (item != values.last()) {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
