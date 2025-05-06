package io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import java.util.Date

@Entity(
    tableName = "assets",
    foreignKeys = [
        ForeignKey(
            entity = AssetGroupEntity::class,
            parentColumns = ["id"],
            childColumns = ["asset_group_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index(value = ["asset_group_id"])]
)
data class AssetEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "asset_class") val assetClass: AssetClass,
    @ColumnInfo(name = "asset_group_id") val assetGroupId: String?,
    @ColumnInfo(name = "acquisition_price") val acquisitionPrice: Double,
    @ColumnInfo(name = "acquisition_date") val acquisitionDate: Date,
    @ColumnInfo(name = "fees") val fees: Double,
    @ColumnInfo(name = "current_value") val currentValue: Pair<Double, Date>,
    @ColumnInfo(name = "former_values") val formerValues: List<Pair<Double, Date>>,
    @ColumnInfo(name = "sell_price") val sellPrice: Double?,
    @ColumnInfo(name = "sell_date") val sellDate: Date?,
    @ColumnInfo(name = "realized_gain") val realizedGain: Double?,
    @ColumnInfo(name = "currency") val currency: Currency,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "user_notes") val userNotes: String?
)

fun AssetEntity.asExternalModel() = Asset(
    id = uid,
    name = name,
    assetClass = assetClass,
    assetGroupId = assetGroupId,
    acquisitionPrice = acquisitionPrice,
    acquisitionDate = acquisitionDate,
    fees = fees,
    currentValue = currentValue,
    formerValues = formerValues,
    sellPrice = sellPrice,
    sellDate = sellDate,
    realizedGain = realizedGain,
    currency = currency,
    url = url,
    userNotes = userNotes,
)