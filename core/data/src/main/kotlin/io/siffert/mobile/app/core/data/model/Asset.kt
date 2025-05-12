package io.siffert.mobile.app.core.data.model

import io.siffert.mobile.app.core.database.dao.AssetWithPriceHistory
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.PriceHistoryDate

fun Asset.asEntity() = AssetEntity(
    uid = id,
    name = name,
    assetClass = assetClass,
    assetGroupId = assetGroupId,
    acquisitionPrice = acquisitionPrice,
    acquisitionDate = acquisitionDate.time,
    fees = fees,
    sellPrice = sellPrice,
    sellDate = sellDate?.time,
    realizedGain = realizedGain,
    currency = currency,
    url = url,
    userNotes = userNotes,
)

fun List<PriceHistoryDate>.asEntity(): List<PriceHistoryEntity> {
    return this.map { priceHistoryDate ->
        PriceHistoryEntity(
            id = priceHistoryDate.id,
            assetId = priceHistoryDate.assetId,
            value = priceHistoryDate.value,
            timestamp = priceHistoryDate.timestamp.time
        )
    }
}


fun Asset.asAssetWithPriceHistory(): AssetWithPriceHistory {
    val assetEntity =
        this.asEntity()

    val priceHistoryEntities = priceHistory.asEntity()


    return AssetWithPriceHistory(
        asset = assetEntity,
        priceHistory = priceHistoryEntities
    )
}