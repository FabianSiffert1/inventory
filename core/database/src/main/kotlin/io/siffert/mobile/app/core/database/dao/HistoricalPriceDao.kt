package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.siffert.mobile.app.core.database.model.HistoricalPriceEntity

@Dao
interface HistoricalPriceDao {
    //todo: add delete
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(values: List<HistoricalPriceEntity>)

    @Query("SELECT * FROM historical_price WHERE assetId = :assetId ORDER BY timestamp ASC")
    suspend fun getHistoricalPricesForAsset(assetId: String): List<HistoricalPriceEntity>
}