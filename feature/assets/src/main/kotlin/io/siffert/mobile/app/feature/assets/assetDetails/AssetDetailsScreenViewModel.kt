package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

sealed interface AssetDetailsScreenUiState {
    data object Loading : AssetDetailsScreenUiState

    data object Error : AssetDetailsScreenUiState

    data object Empty : AssetDetailsScreenUiState

    data class Loaded(val asset: Asset) : AssetDetailsScreenUiState {
        override fun toString(): String {
            return "AssetDetailsScreenUiState.Loaded: $asset"
        }
    }
}

class AssetDetailsScreenViewModel(
    private val assetId: String,
    private val assetRepository: AssetRepository,
) : ViewModel() {

    val uiState: StateFlow<AssetDetailsScreenUiState> =
        assetRepository
            .getAssetById(assetId)
            .onEach { println(it) }
            .map { asset ->
                if (asset != null) {
                    AssetDetailsScreenUiState.Loaded(asset)
                } else {
                    AssetDetailsScreenUiState.Empty
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = AssetDetailsScreenUiState.Loading,
            )
}
