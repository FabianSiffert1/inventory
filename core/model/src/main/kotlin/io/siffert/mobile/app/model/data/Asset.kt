package io.siffert.mobile.app.model.data

data class Asset(
    val id: String,
    val name: String,
    val assetClass: AssetClass,
    val assetGroupId: String?,
    val fees: Double?,
    val priceHistory: List<PriceHistoryEntry>,
    val saleInfo: SaleEntry?,
    val currency: Currency,
    val url: String?,
    val userNotes: String?,
)

fun Asset.isValidAsset(): Boolean {
    return name.isNotEmpty() && priceHistory.isNotEmpty()
}
