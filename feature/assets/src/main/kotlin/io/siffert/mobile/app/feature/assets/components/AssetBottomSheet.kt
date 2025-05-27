package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import kotlinx.coroutines.launch

@Composable
internal fun <T : Enum<T>> AssetBottomSheet(
    values: Array<T>,
    onItemSelected: (T) -> Unit,
    onDismiss: () -> Unit,
    bottomSheetTitle: String,
    sheetState: SheetState,
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        onDismissRequest = onDismiss,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = bottomSheetTitle, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        values.forEach { item ->
            DropdownMenuItem(
                modifier =
                    Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp),
                        ),
                text = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        when (item) {
                            is AssetClassWithStringRes -> {
                                Text(
                                    text = stringResource(item.nameResource),
                                    fontWeight = FontWeight.SemiBold,
                                )
                                AssetClassIcon(item.assetClass)
                            }
                            else -> {
                                Text(text = item.name, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                },
                onClick = {
                    scope
                        .launch {
                            onItemSelected(item)
                            sheetState.hide()
                        }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                },
            )
            if (item != values.last()) {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

// start interactive mode
@Composable
@PreviewLightDark
private fun Preview() = InventoryTheme {
    AssetBottomSheet(
        values = AssetClassWithStringRes.entries.toTypedArray(),
        onItemSelected = {},
        onDismiss = {},
        bottomSheetTitle = "Asset Class",
        sheetState = rememberModalBottomSheetState(),
    )
}
