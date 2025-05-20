package io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model

import androidx.room.Embedded
import androidx.room.Relation
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity
import io.siffert.mobile.app.core.database.model.toExternalModel
import io.siffert.mobile.app.model.data.Asset

data class AssetEntityWithPricesAndSales(
    @Embedded val asset: AssetEntity,
    @Relation(parentColumn = "uid", entityColumn = "assetId") val sale: SalesEntity?,
    @Relation(parentColumn = "uid", entityColumn = "assetId")
    val priceHistory: List<PriceHistoryEntity>,
)

fun AssetEntityWithPricesAndSales.asExternalModel() = asset.asExternalModel(priceHistory, sale)

private fun AssetEntity.asExternalModel(
    priceHistoryEntities: List<PriceHistoryEntity>,
    salesEntity: SalesEntity?,
) =
    Asset(
        id = uid,
        name = name,
        assetClass = assetClass,
        assetGroupId = assetGroupId,
        fees = fees,
        currency = currency,
        saleInfo = salesEntity?.toExternalModel(),
        url = url,
        userNotes = userNotes,
        priceHistory = priceHistoryEntities.map { it.toExternalModel() },
    )
