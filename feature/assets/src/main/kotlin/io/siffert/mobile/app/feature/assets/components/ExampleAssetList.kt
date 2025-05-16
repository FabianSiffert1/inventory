@file:OptIn(ExperimentalUuidApi::class)

package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import io.siffert.mobile.app.model.data.SaleEntry
import kotlin.time.Duration.Companion.days
import kotlin.uuid.ExperimentalUuidApi
import kotlinx.datetime.Clock

private val assetid1 = "assetid1"
private val assetid2 = "assetid2"
private val assetid3 = "assetid3"
private val priceHistoryEntryId1 = "priceHistoryEntryId1"
private val priceHistoryEntryId2 = "priceHistoryEntryId2"
private val priceHistoryEntryId3 = "priceHistoryEntryId3"
private val priceHistoryEntryId4 = "priceHistoryEntryId4"
private val saleEntryId1 = "saleEntryId1"
val exampleAssetList =
    listOf(
        Asset(
            id = assetid1,
            name = "Real Asset",
            assetGroupId = null,
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        id = priceHistoryEntryId1,
                        assetId = assetid1,
                        value = 1.20,
                        timestamp = Clock.System.now(),
                    )
                ),
            saleData = null,
            currency = Currency.EUR,
            url = "https://google.com",
            userNotes = "userNotes1",
        ),
        Asset(
            id = assetid2,
            name = "Security Asset",
            assetGroupId = null,
            assetClass = AssetClass.SECURITY,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        id = priceHistoryEntryId2,
                        assetId = assetid2,
                        value = 1.30,
                        timestamp = Clock.System.now(),
                    )
                ),
            saleData = null,
            currency = Currency.EUR,
            url = null,
            userNotes = "userNotes2",
        ),
        Asset(
            id = assetid3,
            name = "Digital Asset",
            assetGroupId = null,
            assetClass = AssetClass.DIGITAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        id = priceHistoryEntryId3,
                        assetId = assetid3,
                        value = 1.40,
                        timestamp = Clock.System.now().minus(2.days),
                    ),
                    PriceHistoryEntry(
                        id = priceHistoryEntryId4,
                        assetId = assetid3,
                        value = 1.45,
                        timestamp = Clock.System.now(),
                    ),
                ),
            saleData =
                SaleEntry(
                    id = saleEntryId1,
                    assetId = assetid3,
                    value = 1.20,
                    timestamp = Clock.System.now(),
                ),
            currency = Currency.EUR,
            url = null,
            userNotes = null,
        ),
    )
