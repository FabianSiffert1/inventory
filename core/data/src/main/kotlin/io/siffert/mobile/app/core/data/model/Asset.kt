package io.siffert.mobile.app.core.data.model

import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity
import io.siffert.mobile.app.model.data.Asset

fun Asset.asEntity() =
    AssetEntity(
        uid = id,
        name = name,
        assetClass = assetClass,
        assetGroupId = assetGroupId,
        fees = fees,
        sellPrice = sellPrice,
        sellDate = sellDate?.time,
        realizedGain = realizedGain,
        currency = currency,
        url = url,
        userNotes = userNotes,
    )

fun Asset.toPriceHistoryEntities(): List<PriceHistoryEntity> {
    return priceHistory.map { priceHistoryDate ->
        PriceHistoryEntity(
            id = priceHistoryDate.id,
            assetId = this.id,
            value = priceHistoryDate.value,
            timestamp = priceHistoryDate.timestamp.time,
        )
    }
}
