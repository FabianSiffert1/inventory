package io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.model.data.SaleEntry
import kotlinx.datetime.Instant

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
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "assetId") val assetId: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
)

fun SalesEntity.toExternalModel() =
    SaleEntry(
        id = uid,
        assetId = assetId,
        value = value,
        timestamp = Instant.fromEpochMilliseconds(timestamp),
    )
