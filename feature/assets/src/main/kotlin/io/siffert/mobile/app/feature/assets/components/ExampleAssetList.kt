package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryDate
import java.util.Date

val exampleAssetList =
    mutableListOf(
        Asset(
            id = "uuid1",
            name = "asset1",
            assetGroupId = "groupId1",
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryDate(id = 1, assetId = "assetId", value = 1.20, timestamp = Date())
                ),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "currentValue and PnL",
        ),
        Asset(
            id = "uuid2",
            name = "asset2",
            assetGroupId = "groupId2",
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryDate(id = 2, assetId = "assetId", value = 1.30, timestamp = Date())
                ),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "currentValue and PnL",
        ),
        Asset(
            id = "uuid1",
            name = "asset1",
            assetGroupId = "groupId3",
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryDate(id = 3, assetId = "assetId", value = 1.40, timestamp = Date())
                ),
            sellPrice = null,
            sellDate = null,
            realizedGain = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "currentValue and PnL",
        ),
    )
