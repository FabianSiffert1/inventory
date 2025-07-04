package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.assetDetails.priceOverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlin.time.Duration.Companion.days
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

sealed interface PriceOverviewScreenUiState {
  data object Loading : PriceOverviewScreenUiState

  data object Failure : PriceOverviewScreenUiState

  data class Success(
      val assetList: List<PriceHistoryEntry>,
      val assetName: String,
      val assetCurrency: Currency
  ) : PriceOverviewScreenUiState
}

class PriceOverviewViewModel(assetId: String, private val assetRepository: AssetRepository) :
    ViewModel() {

  init {
    viewModelScope.launch {
      assetRepository.getAssetById(assetId).collect { asset ->
        _uiState.value =
            when {
              asset != null ->
                  PriceOverviewScreenUiState.Success(
                      assetList = testData, assetName = asset.name, assetCurrency = asset.currency)
              else -> PriceOverviewScreenUiState.Failure
            }
      }
    }
  }

  val testData =
      listOf(
          PriceHistoryEntry(
              id = "entryId1",
              assetId = "assetId1",
              value =6.50,
              timestamp = Clock.System.now(),
          ),
          PriceHistoryEntry(
              id = "priceHistoryEntryId2",
              assetId = "assetid2",
              value = 132919.12,
              timestamp = Clock.System.now().minus(2.days),
          ),
          PriceHistoryEntry(
              id = "priceHistoryEntryId3",
              assetId = "assetid3",
              value = 132.12,
              timestamp = Clock.System.now().minus(1.days),
          ))

  private val _uiState =
      MutableStateFlow<PriceOverviewScreenUiState>(PriceOverviewScreenUiState.Loading)
  val uiState: StateFlow<PriceOverviewScreenUiState> = _uiState.asStateFlow()
}
