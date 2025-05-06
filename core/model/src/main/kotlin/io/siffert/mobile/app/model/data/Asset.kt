package io.siffert.mobile.app.model.data

import java.util.Date

data class Asset(
    val id: String,
    val name: String,
    val assetClass: AssetClass,
    //todo: create impl that allows users to create and assign groups
    val assetGroup: AssetGroup,
    val acquisitionPrice: Double,
    val acquisitionDate: Date,
    val fees: Double,
    val currentValue: Pair<Double, Date>,
    val formerValues: List<Pair<Double, Date>>,
    val trend: Trend,
    val sellPrice: Double?,
    val sellDate: Date?,
    val unrealizedGain: Double?,
    val realizedGain: Double?,
    val currency: Currency,
    val url: String?,
    // todo: let user set info for assets
    val notes: String?,
    // todo: calculate automatically
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