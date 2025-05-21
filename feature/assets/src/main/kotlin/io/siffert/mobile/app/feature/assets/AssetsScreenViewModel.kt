package io.siffert.mobile.app.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.common.config.AppEnvironment
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components.exampleAssetList
import io.siffert.mobile.app.model.data.Asset
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface AssetsScreenUiState {
    data object Loading : AssetsScreenUiState

    data object Empty : AssetsScreenUiState

    data class Success(val assetList: List<Asset>) : AssetsScreenUiState
}

class AssetsScreenViewModel(
    private val assetRepository: AssetRepository,
    private val appEnvironment: AppEnvironment,
) : ViewModel() {

    /*
    init {
        viewModelScope.launch { assetRepository.upsertAssets(exampleAssetList) }
    }
     */

    val uiState: StateFlow<AssetsScreenUiState> =
        assetRepository
            .getAssetsFlow()
            .map { assets ->
                if (assets.isNullOrEmpty()) return@map AssetsScreenUiState.Empty
                AssetsScreenUiState.Success(assetList = assets)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = AssetsScreenUiState.Loading,
            )

    val isDebug: Boolean = appEnvironment.isDebug

    fun addDebugAssets() {
        viewModelScope.launch { assetRepository.upsertAssets(exampleAssetList) }
    }
}
