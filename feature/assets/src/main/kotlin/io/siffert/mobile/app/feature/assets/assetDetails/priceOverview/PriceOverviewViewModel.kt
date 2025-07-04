package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.seconds

sealed interface PriceOverviewScreenUiState {
    data object Loading : PriceOverviewScreenUiState

    data object Empty : PriceOverviewScreenUiState

    data class Success(val assetList: List<PriceHistoryEntry>) : PriceOverviewScreenUiState
}


class PriceOverviewViewModel(assetId : String) : ViewModel() {

    val testData = PriceOverviewScreenUiState.Success(
        listOf(
            PriceHistoryEntry(
                id = "entryId1",
                assetId = "assetId1",
                value = 1.12312312312321321321312,
                timestamp = Clock.System.now(),
            ),
            PriceHistoryEntry(
                id = "priceHistoryEntryId2",
                assetId = "assetid2",
                value = 132919.12,
                timestamp = Clock.System.now(),
            )
        )
    )

    val uiState: StateFlow<PriceOverviewScreenUiState> =
            flowOf(testData)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = PriceOverviewScreenUiState.Loading,
            )
}