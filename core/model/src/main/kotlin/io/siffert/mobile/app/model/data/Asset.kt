package io.siffert.mobile.app.model.data

import kotlinx.datetime.Instant

data class Asset(
    val id: String,
    val name: String,
    val assetClass: AssetClass,
    val assetGroupId: String?,
    val fees: Double?,
    val priceHistory: List<PriceHistoryDate>,
    val saleData: PriceHistoryDate?,
    val currency: Currency,
    val url: String?,
    val userNotes: String?,
)

enum class AssetClass {
    REAL_ASSET,
    SECURITY,
    DIGITAL_ASSET,
}

data class AssetGroup(val id: String, val name: String)

data class PriceHistoryDate(
    val id: Long,
    val assetId: String,
    val value: Double,
    val timestamp: Instant,
)
