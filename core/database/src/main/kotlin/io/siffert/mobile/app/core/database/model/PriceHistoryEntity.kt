package io.siffert.mobile.app.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.siffert.mobile.app.model.data.PriceHistoryDate
import java.util.Date

@Entity(
    tableName = "price_history",
    foreignKeys = [
        ForeignKey(
            entity = AssetEntity::class,
            parentColumns = ["uid"],
            childColumns = ["assetId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("assetId")]
)
data class PriceHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val assetId: String,
    val value: Double,
    val timestamp: Long
)

fun PriceHistoryEntity.toExternalModel(): PriceHistoryDate = PriceHistoryDate(
    id = id,
    assetId = assetId,
    value = value,
    timestamp = Date(timestamp)
)