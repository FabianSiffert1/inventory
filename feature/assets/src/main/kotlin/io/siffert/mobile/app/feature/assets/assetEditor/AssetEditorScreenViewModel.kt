package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.core.domain.CreateAssetUseCase
import io.siffert.mobile.app.core.domain.UpdateAssetUseCase
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

    data object ShowCurrencyBottomSheet : AssetEditorScreenUiCommand

    data object ShowAssetClassBottomSheet : AssetEditorScreenUiCommand
}

sealed interface AssetProcessingState {
    data object Loading : AssetProcessingState

    data object Failure : AssetProcessingState
}

data class AssetEditorInputs(
    val name: TextFieldValue = TextFieldValue("debugAsset"),
    val fees: TextFieldValue = TextFieldValue(),
    val url: TextFieldValue = TextFieldValue(),
    val notes: TextFieldValue = TextFieldValue(),
    val currentPrice: TextFieldValue = TextFieldValue("123"),
    val assetClassWithStringRes: AssetClassWithStringRes = AssetClassWithStringRes.DIGITAL_ASSET,
    val currency: Currency = Currency.EUR,
)

data class AssetEditorScreenUiState(
    val assetEditorInputs: AssetEditorInputs = AssetEditorInputs(),
    val assetProcessingState: AssetProcessingState? = null,
    val assetToEditState: LoadingState<Asset>? = null,
) {
    private val isValidPrice =
        assetEditorInputs.currentPrice.text.isNotEmpty() &&
            assetEditorInputs.currentPrice.text.toDoubleOrNull() is Double
    private val isValidFee =
        assetEditorInputs.fees.text.isEmpty() ||
            assetEditorInputs.fees.text.toDoubleOrNull() is Double
    val isValidAsset = assetEditorInputs.name.text.isNotEmpty() && isValidPrice && isValidFee
}

class AssetEditorScreenViewModel(
    assetId: String?,
    private val createAssetUseCase: CreateAssetUseCase,
    private val updateAssetUseCase: UpdateAssetUseCase,
    private val assetRepository: AssetRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AssetEditorScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiCommands: Channel<AssetEditorScreenUiCommand> = Channel()
    internal val uiCommands = _uiCommands.receiveAsFlow()

    init {
        if (assetId != null) {
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

    fun showCurrencyBottomSheet() {
        viewModelScope.launch {
            _uiCommands.send((AssetEditorScreenUiCommand.ShowCurrencyBottomSheet))
        }
    }

    fun showAssetClassBottomSheet() {
        viewModelScope.launch {
            _uiCommands.send((AssetEditorScreenUiCommand.ShowAssetClassBottomSheet))
        }
    }

    fun createOrUpdateAsset(assetEditorMode: AssetEditorMode) {
        when (assetEditorMode) {
            AssetEditorMode.CREATE -> createAsset()
            AssetEditorMode.EDIT -> updateAsset()
        }
    }

    private fun createAsset() {
        viewModelScope.launch {
            Log.d("AssetEditorScreenViewModel", "createAsset called")

            val assetData = _uiState.value.toAssetCreationData()
            if (assetData != null) {
                _uiState.update { it.copy(assetProcessingState = AssetProcessingState.Loading) }
                val result = createAssetUseCase.createAsset(assetData)

                when {
                    result.isSuccess -> {
                        updateUiState { AssetEditorScreenUiState() }
                        _uiCommands.send(AssetEditorScreenUiCommand.NavigateBack)
                    }
                    else ->
                        _uiState.update {
                            it.copy(assetProcessingState = AssetProcessingState.Failure)
                        }
                }
            } else {
                _uiState.update { it.copy(assetProcessingState = AssetProcessingState.Failure) }
            }
        }
    }

    private fun updateAsset() {
        viewModelScope.launch {
            Log.d("AssetEditorScreenViewModel", "updateAsset called")

            val uiState = _uiState.value
            val assetInputs = uiState.assetEditorInputs

            val existingAsset = (uiState.assetToEditState as? LoadingState.Present<Asset>)?.value

            if (existingAsset != null) {
                _uiState.update { it.copy(assetProcessingState = AssetProcessingState.Loading) }
                val updatedAsset = assetInputs.toUpdatedAsset(existingAsset)
                val result = updateAssetUseCase.updateAsset(updatedAsset)

                if (result.isSuccess) {
                    _uiCommands.send(AssetEditorScreenUiCommand.NavigateBack)
                } else {
                    _uiState.update { it.copy(assetProcessingState = AssetProcessingState.Failure) }
                }
            }
        }
    }

    private fun updateUiState(update: (AssetEditorScreenUiState) -> AssetEditorScreenUiState) {
        _uiState.update(update)
    }

    fun onNameChange(newName: String) = updateUiState {
        it.copy(assetEditorInputs = it.assetEditorInputs.copy(name = TextFieldValue(newName)))
    }

    fun onPriceChange(newPrice: String) = updateUiState {
        it.copy(
            assetEditorInputs = it.assetEditorInputs.copy(currentPrice = TextFieldValue(newPrice))
        )
    }

    fun onCurrencyChange(newCurrency: Currency) = updateUiState {
        it.copy(assetEditorInputs = it.assetEditorInputs.copy(currency = newCurrency))
    }

    fun onAssetClassChange(newAssetClass: AssetClassWithStringRes) = updateUiState {
        it.copy(
            assetEditorInputs = it.assetEditorInputs.copy(assetClassWithStringRes = newAssetClass)
        )
    }

    fun onFeesChange(newFees: String) = updateUiState {
        it.copy(assetEditorInputs = it.assetEditorInputs.copy(fees = TextFieldValue(newFees)))
    }

    fun onNotesChange(newNotes: String) = updateUiState {
        it.copy(assetEditorInputs = it.assetEditorInputs.copy(notes = TextFieldValue(newNotes)))
    }

    fun onUrlChange(newUrl: String) = updateUiState {
        it.copy(assetEditorInputs = it.assetEditorInputs.copy(url = TextFieldValue(newUrl)))
    }
}
