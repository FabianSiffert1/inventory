package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.compose.ui.text.input.TextFieldValue
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.core.domain.AssetCreationData
import io.siffert.mobile.app.core.domain.PriceHistoryEntryCreationData
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.model.data.Asset
import kotlin.time.Duration.Companion.days
import kotlinx.datetime.Clock

internal fun AssetEditorScreenUiState.toAssetCreationData(): AssetCreationData? {

    val currentPrice = assetEditorInputFields.currentPrice.text.toDoubleOrNull()
    if (!isValidAsset || currentPrice == null) return null

    return AssetCreationData(
        name = assetEditorInputFields.name.text,
        assetClass = assetEditorInputFields.assetClassWithStringRes.assetClass,
        fees = assetEditorInputFields.fees.text.toDoubleOrNull(),
        priceHistory =
            listOf(
                PriceHistoryEntryCreationData(
                    value = currentPrice,
                    timestamp = Clock.System.now() - 10.days,
                )
            ),
        currency = assetEditorInputFields.currency,
        url = assetEditorInputFields.url.text.ifEmpty { null },
        userNotes = assetEditorInputFields.notes.text.ifEmpty { null },
        saleData = null,
        assetGroupId = null,
    )
}

internal fun Asset.toAssetEditorState(): AssetEditorScreenUiState {
    return AssetEditorScreenUiState(
        assetEditorInputFields =
            AssetEditorInputFields(
                name = TextFieldValue(this.name),
                fees = TextFieldValue(this.fees?.toString() ?: ""),
                url = TextFieldValue(this.url ?: ""),
                notes = TextFieldValue(this.userNotes ?: ""),
                currentPrice =
                    TextFieldValue(this.priceHistory.lastOrNull()?.value?.toString() ?: ""),
                assetClassWithStringRes = AssetClassWithStringRes.from(this.assetClass),
                currency = this.currency,
            ),
        assetCreationState = null,
        assetToEditState = LoadingState.Present(this),
    )
}
