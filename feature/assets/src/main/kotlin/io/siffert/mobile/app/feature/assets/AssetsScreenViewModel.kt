package io.siffert.mobile.app.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.Trend
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface AssetsScreenUiState {
    data object Loading : AssetsScreenUiState

    data class Success(val assetList: List<Asset>) : AssetsScreenUiState
}


class AssetsScreenViewModel : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    private val exampleAssetList = mutableListOf(
        Asset(
            id = Uuid.random().toString(),
            name = "asset1",
            notes = "currentValue and PnL",
            trend = Trend.UP
        ),
        Asset(
            id = Uuid.random().toString(),
            name = "asset2",
            notes = "200$ and +20%",
            trend = Trend.FLAT
        ),
        Asset(
            id = Uuid.random().toString(),
            name = "asset3",
            notes = "300$ and -30%",
            trend = Trend.DOWN
        )
    )

    val uiState: StateFlow<AssetsScreenUiState> =
        flow {
            emit(AssetsScreenUiState.Success(assetList = exampleAssetList))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5.seconds),
            initialValue = AssetsScreenUiState.Loading,
        )

}