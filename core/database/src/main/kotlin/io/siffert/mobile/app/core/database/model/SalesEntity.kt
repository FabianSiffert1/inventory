package io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.model.data.PriceHistoryDate
import java.util.Date

@Entity(
    tableName = "sales",
    foreignKeys =
        [
            ForeignKey(
                entity = AssetEntity::class,
                parentColumns = ["uid"],
                childColumns = ["assetId"],
                onDelete = ForeignKey.CASCADE,
            )
        ],
    indices = [Index(value = ["assetId"])],
)
data class SalesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "assetId") val assetId: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
)

fun SalesEntity.toExternalModel() =
    PriceHistoryDate(id = id, assetId = assetId, value = value, timestamp = Date(timestamp))
