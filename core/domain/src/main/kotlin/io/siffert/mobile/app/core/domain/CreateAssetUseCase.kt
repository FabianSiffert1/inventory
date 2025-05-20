@file:OptIn(ExperimentalUuidApi::class)

package io.siffert.mobile.app.core.domain

import android.util.Log
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import io.siffert.mobile.app.model.data.SaleEntry
import io.siffert.mobile.app.model.data.isValidAsset
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.datetime.Instant

class CreateAssetUseCase(private val assetRepository: AssetRepository) {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun createAsset(assetCreationData: AssetCreationData): Result<Unit> {
        // todo: implement logging solution
        Log.d("CreateAssetUseCase", "createAssetUseCase called")
        val assetId = Uuid.random().toString()

        val priceHistory = assetCreationData.priceHistory.map { it.toPriceHistoryEntry(assetId) }

        val saleInfo = assetCreationData.saleData?.toSaleEntry(assetId)

        val asset =
            Asset(
                id = assetId,
                name = assetCreationData.name,
                assetClass = assetCreationData.assetClass,
                assetGroupId = assetCreationData.assetGroupId,
                fees = assetCreationData.fees,
                priceHistory = priceHistory,
                saleInfo = saleInfo,
                currency = assetCreationData.currency,
                url = assetCreationData.url,
                userNotes = assetCreationData.userNotes,
            )
        if (!asset.isValidAsset()) {
            return Result.failure(IllegalArgumentException("Invalid asset"))
        }
        Log.d("CreateAssetUseCase", "Success")
        return assetRepository.upsertAssets(listOf(asset))
    }
}

data class AssetCreationData(
    val name: String,
    val assetClass: AssetClass,
    val assetGroupId: String?,
    val fees: Double?,
    val priceHistory: List<PriceHistoryEntryCreationData>,
    val saleData: SaleEntryCreationData?,
    val currency: Currency,
    val url: String?,
    val userNotes: String?,
)

data class PriceHistoryEntryCreationData(val value: Double, val timestamp: Instant)

data class SaleEntryCreationData(val value: Double, val timestamp: Instant)

private fun PriceHistoryEntryCreationData.toPriceHistoryEntry(assetId: String): PriceHistoryEntry {
    return PriceHistoryEntry(
        id = Uuid.random().toString(),
        assetId = assetId,
        value = value,
        timestamp = timestamp,
    )
}

private fun SaleEntryCreationData.toSaleEntry(assetId: String): SaleEntry {
    return SaleEntry(
        id = Uuid.random().toString(),
        assetId = assetId,
        value = value,
        timestamp = timestamp,
    )
}
