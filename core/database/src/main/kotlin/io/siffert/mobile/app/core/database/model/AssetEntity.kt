package io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryDate
import kotlinx.serialization.Serializable
import java.util.Date

@Entity(
    tableName = "assets",
    foreignKeys = [
        ForeignKey(
            entity = AssetGroupEntity::class,
            parentColumns = ["uid"],
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
    @ColumnInfo(name = "acquisition_date") val acquisitionDate: Long,
    @ColumnInfo(name = "fees") val fees: Double,
    @ColumnInfo(name = "sell_price") val sellPrice: Double?,
    @ColumnInfo(name = "sell_date") val sellDate: Long?,
    @ColumnInfo(name = "realized_gain") val realizedGain: Double?,
    @ColumnInfo(name = "currency") val currency: Currency,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "user_notes") val userNotes: String?
)

@Serializable
data class PriceHistory(
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long
)

fun AssetEntity.asExternalModel(priceHistoryEntities: List<PriceHistoryEntity>) = Asset(
    id = uid,
    name = name,
    assetClass = assetClass,
    assetGroupId = assetGroupId,
    acquisitionPrice = acquisitionPrice,
    acquisitionDate = Date(acquisitionDate),
    fees = fees,
    sellPrice = sellPrice,
    sellDate = sellDate?.let { Date(it) },
    realizedGain = realizedGain,
    currency = currency,
    url = url,
    userNotes = userNotes,
    priceHistory = TODO()
)

fun PriceHistoryEntity.toExternalModel() = PriceHistoryDate(
    value = value,
    timestamp = Date(timestamp)
)

fun PriceHistory.toExternalModel(): PriceHistoryDate =
    PriceHistoryDate(value = value, timestamp = Date(timestamp))

