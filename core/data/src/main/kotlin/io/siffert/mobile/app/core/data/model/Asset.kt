package io.siffert.mobile.app.core.data.model

import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.SalesEntity
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
        currency = currency,
        url = url,
        userNotes = userNotes,
    )

fun Asset.toPriceHistoryEntities(): List<PriceHistoryEntity> {
    return priceHistory.map { priceHistoryDate ->
        PriceHistoryEntity(
            uid = priceHistoryDate.id,
            assetId = priceHistoryDate.assetId,
            value = priceHistoryDate.value,
            timestamp = priceHistoryDate.timestamp.toEpochMilliseconds(),
        )
    }
}

fun Asset.toSalesEntity(): SalesEntity? {
    return saleInfo?.let {
        SalesEntity(
            uid = it.id,
            assetId = it.assetId,
            value = it.value,
            timestamp = it.timestamp.toEpochMilliseconds(),
        )
    }
}
