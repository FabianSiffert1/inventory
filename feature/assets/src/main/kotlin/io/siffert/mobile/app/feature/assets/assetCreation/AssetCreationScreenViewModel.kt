package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AssetCreationScreenUiState
@OptIn(ExperimentalUuidApi::class)
constructor(
    val asset: Asset =
        Asset(
            id = Uuid.random().toString(),
            name = "",
            assetClass = AssetClass.SECURITY,
            assetGroupId = null,
            fees = null,
            priceHistory = emptyList(),
            saleData = null,
            currency = Currency.EUR,
            url = null,
            userNotes = null,
        ),
    val nameInput: TextFieldValue = TextFieldValue(),
    val feesInput: TextFieldValue = TextFieldValue(),
    val urlInput: TextFieldValue = TextFieldValue(),
    val notesInput: TextFieldValue = TextFieldValue(),
) {
    val isValidAsset = asset.name.isNotBlank()
}

class AssetCreationScreenViewModel(private val assetRepository: AssetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetCreationScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(asset = it.asset.copy(name = newName)) }
    }

    fun onFeesChange(newFees: String) {
        val fees = newFees.toDoubleOrNull()
        _uiState.update { it.copy(asset = it.asset.copy(fees = fees)) }
    }

    fun onNotesChange(newNotes: String) {
        _uiState.update { it.copy(asset = it.asset.copy(userNotes = newNotes)) }
    }

    fun onUrlChange(newUrl: String) {
        _uiState.update { it.copy(asset = it.asset.copy(url = newUrl)) }
    }

    fun createAsset() {
        viewModelScope.launch {
            val state = _uiState.value
            println(state.isValidAsset)
            println("name" + state.asset.name)
            if (!state.isValidAsset) return@launch
            // todo: implement return success/failure etc
            uiState.value.asset.let { assetRepository.insertOrIgnoreAsset(listOf(it)) }
            // on success -> navigate back
        }
    }
}
