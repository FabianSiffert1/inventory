package io.siffert.mobile.app.core.data.model

import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.model.data.Asset

fun Asset.asEntity() = AssetEntity(
    uid = id,
    name = name,
    assetClass = assetClass,
    assetGroupId = assetGroupId,
    acquisitionPrice = acquisitionPrice,
    acquisitionDate = acquisitionDate.time,
    fees = fees,
    currentValue = currentValue,
    sellPrice = sellPrice,
    sellDate = sellDate?.time,
    realizedGain = realizedGain,
    currency = currency,
    url = url,
    userNotes = userNotes,
)