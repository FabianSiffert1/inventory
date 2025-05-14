package io.siffert.mobile.app.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.exampleAssetList
import io.siffert.mobile.app.model.data.Asset
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface AssetsScreenUiState {
    data object Loading : AssetsScreenUiState

    data class Success(val assetList: List<Asset>) : AssetsScreenUiState
}

class AssetsScreenViewModel(assetRepository: AssetRepository) : ViewModel() {

    init {
        viewModelScope.launch { assetRepository.upsertAssets(exampleAssetList) }
    }

    val uiState: StateFlow<AssetsScreenUiState> =
        assetRepository
            .getAssetsFlow()
            .onEach { println(it.map { asset -> asset.priceHistory }) }
            .map { AssetsScreenUiState.Success(assetList = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = AssetsScreenUiState.Loading,
            )
}
