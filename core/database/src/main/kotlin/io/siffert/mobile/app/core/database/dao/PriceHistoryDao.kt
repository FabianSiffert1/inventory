package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity

@Dao
interface PriceHistoryDao {
    // todo: add delete
    // todo: fix conflict strategies
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(values: List<PriceHistoryEntity>)

    @Query("SELECT * FROM price_history WHERE assetId = :assetId ORDER BY timestamp ASC")
    suspend fun getHistoricalPricesForAsset(assetId: String): List<PriceHistoryEntity>
}
