package io.siffert.mobile.app.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryDate
import java.util.Date
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface AssetsScreenUiState {
    data object Loading : AssetsScreenUiState

    data class Success(val assetList: List<Asset>) : AssetsScreenUiState
}

class AssetsScreenViewModel(assetRepository: AssetRepository) : ViewModel() {

    private val exampleAssetList =
        mutableListOf(
            Asset(
                id = "assetId1",
                name = "assetName1",
                assetGroupId = null,
                assetClass = AssetClass.REAL_ASSET,
                fees = 0.20,
                sellPrice = null,
                sellDate = null,
                realizedGain = null,
                currency = Currency.EUR,
                url = null,
                userNotes = "userNotes1",
                priceHistory =
                    listOf(
                        PriceHistoryDate(
                            id = 1,
                            assetId = "assetId1",
                            value = 1.10,
                            timestamp = Date(),
                        )
                    ),
            ),
            Asset(
                id = "assetId2",
                name = "assetName2",
                assetGroupId = null,
                assetClass = AssetClass.DIGITAL_ASSET,
                fees = 0.10,
                priceHistory =
                    listOf(
                        PriceHistoryDate(
                            id = 2,
                            assetId = "assetId2",
                            value = 1.20,
                            timestamp = Date(),
                        )
                    ),
                sellPrice = null,
                sellDate = null,
                realizedGain = null,
                currency = Currency.EUR,
                url = null,
                userNotes = "userNotes2",
            ),
            Asset(
                id = "assetId3",
                name = "assetName3",
                assetGroupId = null,
                assetClass = AssetClass.SECURITY,
                fees = 0.10,
                priceHistory =
                    listOf(
                        PriceHistoryDate(
                            id = 4,
                            assetId = "assetId3",
                            value = 1.60,
                            timestamp = Date(),
                        ),
                        PriceHistoryDate(
                            id = 3,
                            assetId = "assetId3",
                            value = 1.30,
                            timestamp = Date(),
                        ),
                    ),
                sellPrice = null,
                sellDate = null,
                realizedGain = null,
                currency = Currency.EUR,
                url = null,
                userNotes = "userNotes3",
            ),
        )

    init {
        viewModelScope.launch { assetRepository.upsertAssets(exampleAssetList) }
    }

    val uiState: StateFlow<AssetsScreenUiState> =
        assetRepository
            .getAssetsFlow()
            .map { AssetsScreenUiState.Success(assetList = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5.seconds),
                initialValue = AssetsScreenUiState.Loading,
            )
}
