package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryDate
import java.util.Date

private val assetid1 = "uud1"
private val assetid2 = "uud2"
private val assetid3 = "uud3"

val exampleAssetList =
    listOf(
        Asset(
            id = assetid1,
            name = "asset1",
            assetGroupId = null,
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryDate(id = 1, assetId = assetid1, value = 1.20, timestamp = Date())
                ),
            saleData = null,
            currency = Currency.EUR,
            url = "https://google.com",
            userNotes = "userNotes1",
        ),
        Asset(
            id = assetid2,
            name = "asset2",
            assetGroupId = null,
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryDate(id = 2, assetId = assetid2, value = 1.30, timestamp = Date())
                ),
            saleData = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "userNotes2",
        ),
        Asset(
            id = assetid3,
            name = "asset3",
            assetGroupId = null,
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryDate(id = 3, assetId = assetid3, value = 1.40, timestamp = Date())
                ),
            saleData =
                PriceHistoryDate(id = 4, assetId = assetid3, value = 1.20, timestamp = Date()),
            currency = Currency.EUR,
            url = null,
            userNotes = null,
        ),
    )
