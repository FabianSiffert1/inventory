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

    val currentPrice = assetEditorInputs.currentPrice.text.toDoubleOrNull()
    if (!isValidAsset || currentPrice == null) return null

    return AssetCreationData(
        name = assetEditorInputs.name.text,
        assetClass = assetEditorInputs.assetClassWithStringRes.assetClass,
        fees = assetEditorInputs.fees.text.toDoubleOrNull(),
        priceHistory =
            listOf(
                PriceHistoryEntryCreationData(
                    value = currentPrice,
                    timestamp = Clock.System.now() - 10.days,
                )
            ),
        currency = assetEditorInputs.currency,
        url = assetEditorInputs.url.text.takeIf { it.isNotBlank() },
        userNotes = assetEditorInputs.notes.text.takeIf { it.isNotBlank() },
        saleData = null,
        assetGroupId = null,
    )
}

fun AssetEditorInputs.toUpdatedAsset(existing: Asset): Asset =
    existing.copy(
        name = name.text,
        fees = fees.text.toDoubleOrNull(),
        url = url.text.takeIf { it.isNotBlank() },
        userNotes = notes.text.takeIf { it.isNotBlank() },
        currency = currency,
        assetClass = assetClassWithStringRes.assetClass,
        // todo: implement for sales and pricehistory changes
    )

internal fun Asset.toAssetEditorState(): AssetEditorScreenUiState =
    AssetEditorScreenUiState(
        assetEditorInputs =
            AssetEditorInputs(
                name = TextFieldValue(name),
                fees = TextFieldValue(fees?.toString().orEmpty()),
                url = TextFieldValue(url.orEmpty()),
                notes = TextFieldValue(userNotes.orEmpty()),
                currentPrice =
                    TextFieldValue(priceHistory.lastOrNull()?.value?.toString().orEmpty()),
                assetClassWithStringRes = AssetClassWithStringRes.from(assetClass),
                currency = currency,
            ),
        assetProcessingState = null,
        assetToEditState = LoadingState.Present(this),
    )
