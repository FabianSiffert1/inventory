package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AssetDetailsScreenUiState {
    data object Loading : AssetDetailsScreenUiState

    data object Error : AssetDetailsScreenUiState

    data object Empty : AssetDetailsScreenUiState

    data class Loaded(
        val asset: Asset,
        val deleteAssetState: DeleteAssetUiState = DeleteAssetUiState.NotStarted,
    ) : AssetDetailsScreenUiState {
        override fun toString(): String {
            return "AssetDetailsScreenUiState.Loaded: $asset"
        }
    }
}

sealed class DeleteAssetUiState {
    data object NotStarted : DeleteAssetUiState()

    data object InProgress : DeleteAssetUiState()

    data object Success : DeleteAssetUiState()

    data object Failure : DeleteAssetUiState()
}

class AssetDetailsScreenViewModel(assetId: String, private val assetRepository: AssetRepository) :
    ViewModel() {

    private val _uiState =
        MutableStateFlow<AssetDetailsScreenUiState>(AssetDetailsScreenUiState.Loading)
    val uiState: StateFlow<AssetDetailsScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            assetRepository.getAssetById(assetId).collect { asset ->
                _uiState.value =
                    when {
                        asset != null -> AssetDetailsScreenUiState.Loaded(asset)
                        else -> AssetDetailsScreenUiState.Empty
                    }
            }
        }
    }

    fun deleteAsset(assetId: String?, navigateBack: () -> Unit) {
        if (assetId == null) return

        val currentState = _uiState.value
        if (currentState !is AssetDetailsScreenUiState.Loaded) return

        _uiState.value = currentState.copy(deleteAssetState = DeleteAssetUiState.InProgress)

        viewModelScope.launch {
            try {
                assetRepository.deleteAssets(assetIds = listOf(assetId))
                _uiState.value = currentState.copy(deleteAssetState = DeleteAssetUiState.Success)
                navigateBack()
            } catch (e: Exception) {
                _uiState.value = currentState.copy(deleteAssetState = DeleteAssetUiState.Failure)
                println("AssetDetailsScreenViewModel: deleteAsset failed: $e")
            }
        }
    }
}
