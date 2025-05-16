package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.domain.AssetCreationData
import io.siffert.mobile.app.core.domain.CreateAssetUseCase
import io.siffert.mobile.app.core.domain.PriceHistoryEntryCreationData
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.model.data.Currency
import kotlin.time.Duration.Companion.days
import kotlin.uuid.ExperimentalUuidApi
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
    val currency: Currency = Currency.EUR,
) {
    private val isValidPrice =
        acquisitionPrice.text.isEmpty() ||
            acquisitionPrice.text.isNotEmpty() && acquisitionPrice.text.toDoubleOrNull() is Double
    private val isValidFee =
        feesInput.text.isEmpty() ||
            feesInput.text.isNotEmpty() && feesInput.text.toDoubleOrNull() is Double
    val isValidAsset = nameInput.text.isNotEmpty() && isValidPrice && isValidFee
}

class AssetCreationScreenViewModel(private val createAssetUseCase: CreateAssetUseCase) :
    ViewModel() {

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

    fun AssetCreationScreenUiState.toAssetCreationData(): AssetCreationData? {
        if (!isValidAsset) return null
        val acquisitionPrice = acquisitionPrice.text.toDoubleOrNull() ?: return null
        val fee = feesInput.text.toDoubleOrNull() ?: return null

        return AssetCreationData(
            name = nameInput.text,
            assetClass = assetClassWithStringRes.assetClass,
            fees = fee,
            priceHistory =
                listOf(
                    PriceHistoryEntryCreationData(
                        value = acquisitionPrice,
                        timestamp = Clock.System.now() - 10.days,
                    )
                ),
            currency = currency,
            url = urlInput.text,
            userNotes = notesInput.text,
            saleData = null,
            assetGroupId = null,
        )
    }

    fun createAsset() {
        viewModelScope.launch {
            val assetData = _uiState.value.toAssetCreationData() ?: return@launch
            val result = createAssetUseCase.createAsset(assetData)
            if (result.isSuccess) {
                updateUiState { AssetCreationScreenUiState() }
            }
            // optionally handle error cases
        }
    }
}
