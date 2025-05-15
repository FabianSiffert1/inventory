package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlin.random.Random
import kotlin.time.Duration.Companion.days
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

data class AssetCreationScreenUiState
@OptIn(ExperimentalUuidApi::class)
constructor(
    val nameInput: TextFieldValue = TextFieldValue(),
    val feesInput: TextFieldValue = TextFieldValue(),
    val urlInput: TextFieldValue = TextFieldValue(),
    val notesInput: TextFieldValue = TextFieldValue(),
    val acquisitionPrice: TextFieldValue = TextFieldValue(),
    val assetClassWithStringRes: AssetClassWithStringRes = AssetClassWithStringRes.DIGITAL_ASSET,
    val currency: Currency? = null,
) {
    private val isValidPrice =
        acquisitionPrice.text.isEmpty() ||
            acquisitionPrice.text.isNotEmpty() && acquisitionPrice.text.toDoubleOrNull() is Double
    private val isValidFee =
        feesInput.text.isEmpty() ||
            feesInput.text.isNotEmpty() && feesInput.text.toDoubleOrNull() is Double
    val isValidAsset = nameInput.text.isNotEmpty() && isValidPrice && isValidFee
}

class AssetCreationScreenViewModel(private val assetRepository: AssetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetCreationScreenUiState())
    val uiState = _uiState.asStateFlow()

    private fun updateUiState(update: (AssetCreationScreenUiState) -> AssetCreationScreenUiState) {
        _uiState.update(update)
    }

    fun onNameChange(newName: String) = updateUiState {
        it.copy(nameInput = TextFieldValue(newName))
    }

    fun onPriceChange(newPrice: String) = updateUiState {
        it.copy(acquisitionPrice = TextFieldValue(newPrice))
    }

    fun onCurrencyChange(newCurrency: Currency) = updateUiState { it.copy(currency = newCurrency) }

    fun onAssetClassChange(newAssetClass: AssetClassWithStringRes) = updateUiState {
        it.copy(assetClassWithStringRes = newAssetClass)
    }

    fun onFeesChange(newFees: String) = updateUiState {
        it.copy(feesInput = TextFieldValue(newFees))
    }

    fun onNotesChange(newNotes: String) = updateUiState {
        it.copy(notesInput = TextFieldValue(newNotes))
    }

    fun onUrlChange(newUrl: String) = updateUiState { it.copy(urlInput = TextFieldValue(newUrl)) }

    @OptIn(ExperimentalUuidApi::class)
    fun createAsset() {
        viewModelScope.launch {
            val uiState = _uiState.value
            if (!uiState.isValidAsset) return@launch
            val currency = uiState.currency ?: return@launch
            val assetId = Uuid.random().toString()
            val asset =
                Asset(
                    id = assetId,
                    name = uiState.nameInput.text,
                    assetClass = uiState.assetClassWithStringRes.assetClass,
                    assetGroupId = null,
                    fees = 0.01,
                    priceHistory =
                        listOf(
                            PriceHistoryEntry(
                                id = Random.nextLong(),
                                assetId = assetId,
                                value = uiState.acquisitionPrice.text.toDouble(),
                                timestamp = Clock.System.now().minus(2.days),
                            )
                        ),
                    saleData = null,
                    currency = currency,
                    url = uiState.urlInput.text,
                    userNotes = uiState.notesInput.text,
                )
            // todo: implement return success/failure etc
            assetRepository.upsertAssets(listOf(asset))
            // on success -> navigate back
            // on success -> clean inputs
            updateUiState { AssetCreationScreenUiState() }
        }
    }
}
