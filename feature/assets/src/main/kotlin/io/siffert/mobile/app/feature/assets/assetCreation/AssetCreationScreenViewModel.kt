package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetCreation

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AssetCreationScreenUiState(
    val textInputs: Map<AssetCreationTextField, TextFieldValue> = emptyMap(),
    val asset: Asset? = null,
)

enum class AssetCreationTextField {
    NAME,
    ACQUISITION_PRICE,
    ACQUISITION_DATE,
    FEES,
    URL,
    NOTES,
}

class AssetCreationScreenViewModel(private val assetRepository: AssetRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetCreationScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onInputChange(field: AssetCreationTextField, newValue: String) {
        _uiState.update { currentState ->
            val updatedMap = currentState.textInputs.toMutableMap()
            updatedMap[field] = TextFieldValue(newValue)
            currentState.copy(textInputs = updatedMap)
        }
    }

    fun onInputComplete(field: AssetCreationTextField) {
        if (field == AssetCreationTextField.NAME) {
            val name = _uiState.value.textInputs[AssetCreationTextField.NAME]?.text ?: ""
            val updatedAsset = _uiState.value.asset?.copy(name = name)
            _uiState.update { it.copy(asset = updatedAsset) }
        }
    }

    suspend fun createAsset() {
        // todo: implement return success/failure etc
        if (uiState.value.asset == null) return
        uiState.value.asset?.let { assetRepository.insertOrIgnoreAsset(listOf(it)) }
    }
}
