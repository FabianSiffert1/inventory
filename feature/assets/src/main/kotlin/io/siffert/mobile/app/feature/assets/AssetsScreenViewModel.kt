package io.siffert.mobile.app.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Trend
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

sealed interface AssetsScreenUiState {
    data object Loading : AssetsScreenUiState

    data class Success(val assetList: List<Asset>) : AssetsScreenUiState
}


class AssetsScreenViewModel : ViewModel() {

    private val exampleAssetList = mutableListOf(
        Asset(
            name = "asset1", info = "currentValue and PnL", trend = Trend.UP
        ),
        Asset(
            name = "asset2", info = "200$ and +20%", trend = Trend.FLAT
        ),
        Asset(
            name = "asset3", info = "300$ and -30%", trend = Trend.DOWN
        )
    )

    val uiState: StateFlow<AssetsScreenUiState> =
        flow {
            emit(AssetsScreenUiState.Success(assetList = exampleAssetList))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AssetsScreenUiState.Loading,
        )

}