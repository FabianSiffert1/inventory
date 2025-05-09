package io.siffert.mobile.app.feature.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.siffert.mobile.app.core.database.InventoryAppDatabase
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.PriceHistory
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryDate
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface AssetsScreenUiState {
    data object Loading : AssetsScreenUiState
    data class Success(val assetList: List<Asset>) : AssetsScreenUiState
}


@OptIn(ExperimentalUuidApi::class)
class AssetsScreenViewModel(inventoryAppDatabase: InventoryAppDatabase) : ViewModel() {

    //todo move to core:data module and delete this and everything that belongs to it
    private val assetDao = inventoryAppDatabase.assetDao()

    init {
        viewModelScope.launch {
            assetDao.upsertAssets(
                listOf(
                    AssetEntity(
                        uid = Uuid.random().toString(),
                        name = "asset1",
                        assetClass = AssetClass.REAL_ASSET,
                        assetGroupId = null,
                        acquisitionPrice = 1.00,
                        acquisitionDate = 230123,
                        fees = 0.0,
                        priceHistory = PriceHistory(1.2, 230124),
                        sellPrice = null,
                        sellDate = null,
                        realizedGain = null,
                        currency = Currency.EUR,
                        url = null,
                        userNotes = null,
                    )
                )
            )
            val allAssets = assetDao.getAssetsList()
            allAssets.forEach { asset ->
                println("*** ${asset.name}")
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private val exampleAssetList = mutableListOf(
        Asset(
            id = Uuid.random().toString(),
            name = "asset1",
            assetGroupId = Uuid.random().toString(),
            assetClass = AssetClass.REAL_ASSET,
            acquisitionPrice = 1.00,
            acquisitionDate = Date(),
            fees = 0.10,
            priceHistory = listOf(PriceHistoryDate(1.20, Date())),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "userNotes1",
        ),
        Asset(
            id = Uuid.random().toString(),
            name = "asset2",
            assetGroupId = Uuid.random().toString(),
            assetClass = AssetClass.REAL_ASSET,
            acquisitionPrice = 1.00,
            acquisitionDate = Date(),
            fees = 0.10,
            priceHistory = listOf(PriceHistoryDate(1.20, Date())),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "userNotes2",
        ),
        Asset(
            id = Uuid.random().toString(),
            name = "asset3",
            assetGroupId = Uuid.random().toString(),
            assetClass = AssetClass.REAL_ASSET,
            acquisitionPrice = 1.00,
            acquisitionDate = Date(),
            fees = 0.10,
            priceHistory = listOf(PriceHistoryDate(1.20, Date())),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "userNotes3",
        ),
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