package io.siffert.mobile.app.model.data

import java.util.Date

data class Asset(
    val id: String,
    val name: String,
    val assetClass: AssetClass,
    val assetGroupId: String?,
    val acquisitionPrice: Double,
    val acquisitionDate: Date,
    val fees: Double,
    val priceHistory: List<PriceHistoryDate>,
    val sellPrice: Double?,
    val sellDate: Date?,
    val realizedGain: Double?,
    val currency: Currency,
    val url: String?,
    val userNotes: String?
)

enum class AssetClass {
    REAL_ASSET,
    SECURITY,
    DIGITAL_ASSET,
}

data class AssetGroup(
    val id: String,
    val name: String,
)

data class PriceHistoryDate(
    val value: Double,
    val timestamp: Date,
)
