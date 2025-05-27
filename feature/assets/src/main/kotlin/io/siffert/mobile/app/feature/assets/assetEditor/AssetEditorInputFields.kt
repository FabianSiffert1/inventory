package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetBottomSheetListItem
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassIcon
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetTextField
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
    if (uiState.assetCreationState == AssetCreationState.Failure) {
        AssetEditingFailedNotificationItem()
    }
    AssetTextField(
        input = uiState.assetEditorInputFields.name.text,
        onInputChange = onNameChange,
        inputLabel = stringResource(id = R.string.feature_assets_editor_name),
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.currentPrice.text,
        inputLabel = stringResource(id = R.string.feature_assets_editor_price),
        onInputChange = onPriceChange,
        numericOnly = true,
    )
    AssetBottomSheetListItem(
        toggleBottomSheet = toggleCurrencyBottomSheet,
        currentlySelectedItemName = uiState.assetEditorInputFields.currency.name,
        showBottomSheet = isCurrencyBottomSheetVisible,
        onItemClick = onCurrencyChange,
        enumEntries = Currency.entries.toTypedArray(),
        label = stringResource(id = R.string.feature_assets_editor_currency),
    )

    AssetBottomSheetListItem(
        toggleBottomSheet = toggleAssetClassBottomSheet,
        currentlySelectedItemName =
            stringResource(
                id = uiState.assetEditorInputFields.assetClassWithStringRes.nameResource
            ),
        showBottomSheet = isAssetClassBottomSheetVisible,
        label = stringResource(id = R.string.feature_assets_editor_asset_class),
        enumEntries = AssetClassWithStringRes.entries.toTypedArray(),
        onItemClick = onAssetClassChange,
        trailingContent = {
            AssetClassIcon(uiState.assetEditorInputFields.assetClassWithStringRes.assetClass)
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
        input = uiState.assetEditorInputFields.fees.text,
        inputLabel = stringResource(id = R.string.feature_assets_editor_fees),
        onInputChange = onFeesChange,
        numericOnly = true,
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.url.text,
        onInputChange = onUrlChange,
        inputLabel = stringResource(id = R.string.feature_assets_editor_url),
    )
    AssetTextField(
        input = uiState.assetEditorInputFields.notes.text,
        onInputChange = onNotesChange,
        inputLabel = stringResource(id = R.string.feature_assets_editor_notes),
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
