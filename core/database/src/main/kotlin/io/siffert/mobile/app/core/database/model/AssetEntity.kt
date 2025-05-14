package io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.SalesEntity
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.toExternalModel
import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency

@Entity(
    tableName = "assets",
    foreignKeys =
        [
            ForeignKey(
                entity = AssetGroupEntity::class,
                parentColumns = ["uid"],
                childColumns = ["asset_group_id"],
                onDelete = ForeignKey.SET_NULL,
            )
        ],
    indices = [Index(value = ["asset_group_id"])],
)
data class AssetEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "asset_class") val assetClass: AssetClass,
    @ColumnInfo(name = "asset_group_id") val assetGroupId: String?,
    @ColumnInfo(name = "fees") val fees: Double?,
    @ColumnInfo(name = "currency") val currency: Currency,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "user_notes") val userNotes: String?,
)

fun AssetEntity.asExternalModel(
    priceHistoryEntities: List<PriceHistoryEntity>,
    salesEntity: SalesEntity?,
) =
    Asset(
        id = uid,
        name = name,
        assetClass = assetClass,
        assetGroupId = assetGroupId,
        fees = fees,
        currency = currency,
        saleData = salesEntity?.toExternalModel(),
        url = url,
        userNotes = userNotes,
        priceHistory = priceHistoryEntities.map { it.toExternalModel() },
    )

data class AssetWithPriceHistoryAndSales(
    @Embedded val asset: AssetEntity,
    @Relation(parentColumn = "uid", entityColumn = "assetId") val sale: SalesEntity?,
    @Relation(parentColumn = "uid", entityColumn = "assetId")
    val priceHistory: List<PriceHistoryEntity>,
)

fun AssetWithPriceHistoryAndSales.asExternalModel() = asset.asExternalModel(priceHistory, sale)
