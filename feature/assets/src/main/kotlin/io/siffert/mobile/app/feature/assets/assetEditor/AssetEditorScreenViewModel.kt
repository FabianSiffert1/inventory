package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetEditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.common.flow.LoadingState
import io.siffert.mobile.app.core.common.flow.LoadingState.Present
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AssetEditorScreenUiState(val asset: LoadingState<Asset> = LoadingState.Loading)

class AssetEditorScreenViewModel(assetId: String, private val assetRepository: AssetRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(AssetEditorScreenUiState())
    val uiState: StateFlow<AssetEditorScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            assetRepository.getAssetById(assetId).collect { asset ->
                _uiState.value =
                    when {
                        asset != null -> AssetEditorScreenUiState(asset = Present(asset))
                        else -> AssetEditorScreenUiState(asset = LoadingState.NotPresent)
                    }
            }
        }
    }

    fun saveAsset() {
        println("placeholder")
    }
}
