package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.AssetWithPriceHistoryAndSales
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {
    @Transaction
    @Query("SELECT * FROM assets WHERE uid = :assetId")
    suspend fun getAssetWithPriceHistoryAndSales(assetId: String): AssetWithPriceHistoryAndSales?

    @Transaction
    @Query("SELECT * FROM assets WHERE uid = :assetId")
    fun getAssetWithPriceHistoryAndSalesFlow(assetId: String): Flow<AssetWithPriceHistoryAndSales?>

    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertAsset(asset: AssetEntity)

    @Insert suspend fun insertPriceHistory(history: PriceHistoryEntity)

    @Transaction
    @Query("SELECT * FROM assets")
    fun getAssetsWithPriceHistoryAndSalesFlow(): Flow<List<AssetWithPriceHistoryAndSales>>

    @Transaction
    @Query("SELECT * FROM assets")
    fun getAssetsWithPriceHistoryAndSales(): List<AssetWithPriceHistoryAndSales>

    @Query("SELECT * FROM assets WHERE uid IN (:ids)")
    fun getAssetsWithPriceHistoryAndSalesByIds(
        ids: Set<String>
    ): Flow<List<AssetWithPriceHistoryAndSales?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreAssets(assetEntities: List<AssetEntity>): List<Long>

    @Upsert suspend fun upsertAssets(assets: List<AssetEntity>)

    @Query("DELETE FROM assets WHERE uid in (:ids)") suspend fun deleteAssets(ids: List<String>)
}
