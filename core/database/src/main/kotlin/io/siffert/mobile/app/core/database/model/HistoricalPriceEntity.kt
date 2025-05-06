package io.siffert.mobile.app.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "historical_price",
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
data class HistoricalPriceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val assetId: String,
    val value: Double,
    val timestamp: Long
)