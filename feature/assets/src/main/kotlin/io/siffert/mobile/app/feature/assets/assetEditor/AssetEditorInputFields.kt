package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetBottomSheetListItem
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetTextField
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryBackground
import io.siffert.mobile.app.inventory.core.designsystem.theme.InventoryTheme
import io.siffert.mobile.app.model.data.Currency

@Composable
internal fun AssetEditorInputFields(
    uiState: AssetEditorScreenUiState,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    isCurrencyBottomSheetVisible: Boolean,
    toggleCurrencyBottomSheet: () -> Unit,
    toggleAssetClassBottomSheet: () -> Unit,
    onCurrencyChange: (Currency) -> Unit,
    isAssetClassBottomSheetVisible: Boolean,
    onAssetClassChange: (AssetClassWithStringRes) -> Unit,
    onFeesChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
) {
    if (uiState.assetProcessingState == AssetProcessingState.Failure) {
        AssetEditingFailedNotificationItem()
    }
    AssetTextField(
        input = uiState.assetEditorInputs.name.text,
        onInputChange = onNameChange,
        inputLabel = stringResource(id = R.string.feature_assets_editor_name),
        isNoteBlock = true,
    )
    AssetTextField(
        input = uiState.assetEditorInputs.currentPrice.text,
        inputLabel = stringResource(id = R.string.feature_assets_editor_price),
        onInputChange = onPriceChange,
        numericOnly = true,
    )
    AssetBottomSheetListItem(
        toggleBottomSheet = toggleCurrencyBottomSheet,
        currentlySelectedItemName = uiState.assetEditorInputs.currency.name,
        showBottomSheet = isCurrencyBottomSheetVisible,
        onItemClick = onCurrencyChange,
        enumEntries = Currency.entries.toTypedArray(),
        label = stringResource(id = R.string.feature_assets_editor_currency),
    )

    AssetBottomSheetListItem(
        toggleBottomSheet = toggleAssetClassBottomSheet,
        currentlySelectedItemName =
            stringResource(id = uiState.assetEditorInputs.assetClassWithStringRes.nameResource),
        showBottomSheet = isAssetClassBottomSheetVisible,
        label = stringResource(id = R.string.feature_assets_editor_asset_class),
        enumEntries = AssetClassWithStringRes.entries.toTypedArray(),
        onItemClick = onAssetClassChange,
        trailingContent = {
            AssetClassIcon(uiState.assetEditorInputs.assetClassWithStringRes.assetClass)
        },
    )
    HorizontalDivider()
    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = stringResource(id = R.string.feature_assets_editor_optional),
        fontSize = MaterialTheme.typography.labelSmall.fontSize,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Left,
    )
    AssetTextField(
        input = uiState.assetEditorInputs.fees.text,
        inputLabel = stringResource(id = R.string.feature_assets_editor_fees),
        onInputChange = onFeesChange,
        numericOnly = true,
    )
    AssetTextField(
        input = uiState.assetEditorInputs.url.text,
        onInputChange = onUrlChange,
        inputLabel = stringResource(id = R.string.feature_assets_editor_url),
        isNoteBlock = true,
    )
    AssetTextField(
        input = uiState.assetEditorInputs.notes.text,
        onInputChange = onNotesChange,
        inputLabel = stringResource(id = R.string.feature_assets_editor_notes),
        isNoteBlock = true,
    )
}

@Composable
private fun AssetEditingFailedNotificationItem() {
    ListItem(
        modifier =
            Modifier.clip(RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(8.dp)),
        headlineContent = {
            Text(stringResource(id = R.string.feature_assets_editor_failed_title))
        },
        supportingContent = {
            Text(stringResource(id = R.string.feature_assets_editor_failed_subtitle))
        },
    )
}

@PreviewLightDark
@Composable
private fun Preview() = InventoryTheme {
    InventoryBackground {
        Column(
            modifier =
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AssetEditorInputFields(
                uiState =
                    AssetEditorScreenUiState(
                        assetEditorInputs =
                            AssetEditorInputs(
                                name = TextFieldValue("name"),
                                fees = TextFieldValue(),
                                url = TextFieldValue(),
                                notes = TextFieldValue("Notes"),
                                currentPrice = TextFieldValue("2.50"),
                                assetClassWithStringRes = AssetClassWithStringRes.REAL_ASSET,
                                currency = Currency.EUR,
                            ),
                        assetProcessingState = null,
                        assetToEditState = null,
                    ),
                onNameChange = {},
                onPriceChange = {},
                isCurrencyBottomSheetVisible = false,
                toggleCurrencyBottomSheet = {},
                toggleAssetClassBottomSheet = {},
                onCurrencyChange = {},
                isAssetClassBottomSheetVisible = false,
                onAssetClassChange = {},
                onFeesChange = {},
                onUrlChange = {},
                onNotesChange = {},
            )
        }
    }
}
