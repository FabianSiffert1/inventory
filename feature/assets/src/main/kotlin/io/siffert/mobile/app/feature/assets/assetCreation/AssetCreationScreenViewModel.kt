package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
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
    val assetClass: AssetClass = AssetClass.REAL_ASSET,
    // todo reset default to null
    val currency: Currency? = Currency.EUR,
) {
    private val isValidPrice =
        acquisitionPrice.text.isEmpty() ||
            acquisitionPrice.text.isNotEmpty() && acquisitionPrice.text.toDoubleOrNull() is Double
    private val isValidFee =
        feesInput.text.isEmpty() ||
            feesInput.text.isNotEmpty() && feesInput.text.toDoubleOrNull() is Double
    val isValidAsset = nameInput.text.isNotEmpty() && currency != null && isValidPrice && isValidFee
}

class AssetCreationScreenViewModel(private val assetRepository: AssetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetCreationScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(nameInput = TextFieldValue(newName)) }
    }

    fun onAssetClassChange(newAssetClass: AssetClass) {
        _uiState.update { it.copy(assetClass = newAssetClass) }
    }

    fun onFeesChange(newFees: String) {
        val fees = newFees.toDoubleOrNull()
        _uiState.update { it.copy(feesInput = TextFieldValue(fees.toString())) }
    }

    fun onNotesChange(newNotes: String) {
        _uiState.update { it.copy(notesInput = TextFieldValue(newNotes)) }
    }

    fun onUrlChange(newUrl: String) {
        _uiState.update { it.copy(urlInput = TextFieldValue(newUrl)) }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun createAsset() {
        viewModelScope.launch {
            _uiState.update { it.copy(acquisitionPrice = TextFieldValue("2.3")) }
            val uiState = _uiState.value
            if (!uiState.isValidAsset) return@launch
            println(uiState.acquisitionPrice.text)
            val assetId = Uuid.random().toString()
            val asset =
                Asset(
                    id = assetId,
                    name = uiState.nameInput.text,
                    assetClass = uiState.assetClass,
                    assetGroupId = null,
                    fees = 0.01,
                    priceHistory =
                        listOf(
                            PriceHistoryEntry(
                                id = Random.nextLong(),
                                assetId = assetId,
                                value = uiState.acquisitionPrice.text.toDouble(),
                                timestamp = Clock.System.now().minus(2.days),
                            ),
                            PriceHistoryEntry(
                                id = Random.nextLong(),
                                assetId = assetId,
                                value = 1.0,
                                timestamp = Clock.System.now(),
                            ),
                        ),
                    saleData = null,
                    currency = Currency.EUR,
                    url = uiState.urlInput.text,
                    userNotes = uiState.notesInput.text,
                )
            // todo: implement return success/failure etc
            assetRepository.upsertAssets(listOf(asset))
            // on success -> navigate back
            // on success -> clean inputs
            _uiState.update {
                it.copy(
                    nameInput = TextFieldValue(),
                    feesInput = TextFieldValue(),
                    urlInput = TextFieldValue(),
                    notesInput = TextFieldValue(),
                )
            }
        }
    }
}
