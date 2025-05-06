package io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.AssetGroup
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.Trend
import java.util.Date

@Entity
data class AssetEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "asset_class") val assetClass: AssetClass,
    @ColumnInfo(name = "asset_group_id") val assetGroupId: AssetGroup,
    @ColumnInfo(name = "asset_group_name") val assetGroupName: String,
    @ColumnInfo(name = "acquisition_price") val acquisitionPrice: Double,
    @ColumnInfo(name = "acquisition_date") val acquisitionDate: Date,
    @ColumnInfo(name = "fees") val fees: Double,
    @ColumnInfo(name = "current_value") val currentValue: Pair<Double, Date>,
    @ColumnInfo(name = "former_values") val formerValues: List<Pair<Double, Date>>,
    @ColumnInfo(name = "trend") val trend: Trend,
    @ColumnInfo(name = "sell_price") val sellPrice: Double?,
    @ColumnInfo(name = "sell_date") val sellDate: Date?,
    @ColumnInfo(name = "unrealized_gain") val unrealizedGain: Double?,
    @ColumnInfo(name = "realized_gain") val realizedGain: Double?,
    @ColumnInfo(name = "currency") val currency: Currency,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "notes") val notes: String?
)

fun AssetEntity.asExternalModel() = Asset(
    id = uid,
    name = name,
    assetClass = assetClass,
    assetGroup = assetGroupId,
    acquisitionPrice = acquisitionPrice,
    acquisitionDate = acquisitionDate,
    fees = fees,
    currentValue = currentValue,
    formerValues = formerValues,
    trend = trend,
    sellPrice = sellPrice,
    sellDate = sellDate,
    unrealizedGain = unrealizedGain,
    realizedGain = realizedGain,
    currency = currency,
    url = url,
    notes = notes
)