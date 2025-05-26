package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.core.domain.CreateAssetUseCase
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.AssetClassWithStringRes
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Currency
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AssetEditorMode {
    CREATE,
    EDIT,
}

sealed interface AssetEditorScreenUiCommand {
    data object NavigateBack : AssetEditorScreenUiCommand
}

sealed interface AssetCreationState {
    data object Loading : AssetCreationState

    data object Failure : AssetCreationState
}

data class AssetEditorInputFields(
    val name: TextFieldValue = TextFieldValue("debugAsset"),
    val fees: TextFieldValue = TextFieldValue(),
    val url: TextFieldValue = TextFieldValue(),
    val notes: TextFieldValue = TextFieldValue(),
    val currentPrice: TextFieldValue = TextFieldValue("123"),
    val assetClassWithStringRes: AssetClassWithStringRes = AssetClassWithStringRes.DIGITAL_ASSET,
    val currency: Currency = Currency.EUR,
)

data class AssetEditorScreenUiState(
    val assetEditorInputFields: AssetEditorInputFields = AssetEditorInputFields(),
    val assetCreationState: AssetCreationState? = null,
    val assetToEditState: LoadingState<Asset>? = null,
) {
    private val isValidPrice =
        assetEditorInputFields.currentPrice.text.isNotEmpty() &&
            assetEditorInputFields.currentPrice.text.toDoubleOrNull() is Double
    private val isValidFee =
        assetEditorInputFields.fees.text.isEmpty() ||
            assetEditorInputFields.fees.text.toDoubleOrNull() is Double
    val isValidAsset = assetEditorInputFields.name.text.isNotEmpty() && isValidPrice && isValidFee
}

class AssetEditorScreenViewModel(
    assetId: String?,
    assetEditorMode: AssetEditorMode,
    private val createAssetUseCase: CreateAssetUseCase,
    private val assetRepository: AssetRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetEditorScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiCommands: Channel<AssetEditorScreenUiCommand> = Channel()
    internal val uiCommands = _uiCommands.receiveAsFlow()

    init {
        if (assetId != null) {
            // todo add ui loading state for input fields
            viewModelScope.launch {
                updateUiState { it.copy(assetToEditState = LoadingState.Loading) }
                assetRepository.getAssetById(assetId).collect { asset ->
                    if (asset != null) {
                        updateUiState { asset.toAssetEditorState() }
                    } else {
                        updateUiState { it.copy(assetToEditState = LoadingState.NotPresent) }
                    }
                }
            }
        }
    }

    private fun updateUiState(update: (AssetEditorScreenUiState) -> AssetEditorScreenUiState) {
        _uiState.update(update)
    }

    fun onNameChange(newName: String) = updateUiState {
        it.copy(
            assetEditorInputFields = it.assetEditorInputFields.copy(name = TextFieldValue(newName))
        )
    }

    fun onPriceChange(newPrice: String) = updateUiState {
        it.copy(
            assetEditorInputFields =
                it.assetEditorInputFields.copy(currentPrice = TextFieldValue(newPrice))
        )
    }

    fun onCurrencyChange(newCurrency: Currency) = updateUiState {
        it.copy(assetEditorInputFields = it.assetEditorInputFields.copy(currency = newCurrency))
    }

    fun onAssetClassChange(newAssetClass: AssetClassWithStringRes) = updateUiState {
        it.copy(
            assetEditorInputFields =
                it.assetEditorInputFields.copy(assetClassWithStringRes = newAssetClass)
        )
    }

    fun onFeesChange(newFees: String) = updateUiState {
        it.copy(
            assetEditorInputFields = it.assetEditorInputFields.copy(fees = TextFieldValue(newFees))
        )
    }

    fun onNotesChange(newNotes: String) = updateUiState {
        it.copy(
            assetEditorInputFields =
                it.assetEditorInputFields.copy(notes = TextFieldValue(newNotes))
        )
    }

    fun onUrlChange(newUrl: String) = updateUiState {
        it.copy(
            assetEditorInputFields = it.assetEditorInputFields.copy(url = TextFieldValue(newUrl))
        )
    }

    fun createAsset() {
        viewModelScope.launch {
            Log.d("AssetCreationScreenViewModel", "createAsset called")

            val assetData = _uiState.value.toAssetCreationData()
            if (assetData != null) {
                _uiState.update { it.copy(assetCreationState = AssetCreationState.Loading) }
                val result = createAssetUseCase.createAsset(assetData)

                when {
                    result.isSuccess -> {
                        updateUiState { AssetEditorScreenUiState() }
                        _uiCommands.send(AssetEditorScreenUiCommand.NavigateBack)
                    }
                    else ->
                        _uiState.update { it.copy(assetCreationState = AssetCreationState.Failure) }
                }
            } else {
                _uiState.update { it.copy(assetCreationState = AssetCreationState.Failure) }
            }
        }
    }
}
