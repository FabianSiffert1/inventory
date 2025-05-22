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
) {
    operator fun iterator(): Iterator<Pair<String, Any?>> {
        return listOf(
                "id" to id,
                "name" to name,
                "assetClass" to assetClass,
                "assetGroupId" to assetGroupId,
                "fees" to fees,
                "priceHistory" to priceHistory,
                "saleInfo" to saleInfo,
                "currency" to currency,
                "url" to url,
                "userNotes" to userNotes,
            )
            .iterator()
    }
}

fun Asset.isValidAsset(): Boolean {
    return name.isNotEmpty() && priceHistory.isNotEmpty()
}
