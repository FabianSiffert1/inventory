package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T : Enum<T>> EnumDropdownMenu(
    values: Array<T>,
    currentlySelectedAssetClass: String,
    onItemSelected: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        ListItem(
            modifier =
                Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .clickable(enabled = true, onClick = { expanded = !expanded }),
            colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface),
            headlineContent = { Text(text = currentlySelectedAssetClass) },
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
                            ),
                    text = { Text(item.name) },
                    onClick = {
                        expanded = false
                        onItemSelected(item)
                    },
                )
                if (item != values.last()) {
                    HorizontalDivider()
                }
            }
        }
    }
}
