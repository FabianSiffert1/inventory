@file:OptIn(ExperimentalUuidApi::class)

package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days
import kotlin.uuid.ExperimentalUuidApi

private val assetid1 = "assetid1"
private val assetid2 = "assetid2"
private val assetid3 = "assetid3"
private val priceHistoryDateId1 = 1L
private val priceHistoryDateId2 = 2L
private val priceHistoryDateId3 = 3L
private val priceHistoryDateId4 = 4L
private val priceHistoryDateId5 = 5L

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
                        id = priceHistoryDateId1,
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
                        id = priceHistoryDateId2,
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
                        id = priceHistoryDateId3,
                        assetId = assetid3,
                        value = 1.40,
                        timestamp = Clock.System.now().minus(2.days),
                    ),
                    PriceHistoryEntry(
                        id = priceHistoryDateId5,
                        assetId = assetid3,
                        value = 1.45,
                        timestamp = Clock.System.now(),
                    ),
                ),
            saleData =
                PriceHistoryEntry(
                    id = priceHistoryDateId4,
                    assetId = assetid3,
                    value = 1.20,
                    timestamp = Clock.System.now(),
                ),
            currency = Currency.EUR,
            url = null,
            userNotes = null,
        ),
    )
