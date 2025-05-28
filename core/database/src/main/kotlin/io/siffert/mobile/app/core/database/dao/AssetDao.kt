package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.AssetEntityWithPricesAndSales
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.SalesEntity
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {

    @Transaction
    @Query("SELECT * FROM assets WHERE uid = :assetId")
    fun getAssetWithPriceHistoryAndSalesFlow(assetId: String): Flow<AssetEntityWithPricesAndSales?>

    @Transaction
    @Query("SELECT * FROM assets")
    fun getAssetsWithPriceHistoryAndSalesFlow(): Flow<List<AssetEntityWithPricesAndSales>>

    @Transaction
    @Query("SELECT * FROM assets")
    suspend fun getAssetsWithPriceHistoryAndSales(): List<AssetEntityWithPricesAndSales>

    @Transaction
    suspend fun updateAssetsWithHistoryAndSales(
        assets: List<AssetEntity>,
        priceHistories: List<PriceHistoryEntity>,
        sales: List<SalesEntity>,
    ) {
        updateAssets(assets)
        updatePriceHistory(priceHistories)
        updateSales(sales)
    }

    @Update suspend fun updateAssets(asset: List<AssetEntity>): Int

    @Update suspend fun updatePriceHistory(priceHistory: List<PriceHistoryEntity>): Int

    @Update suspend fun updateSales(sales: List<SalesEntity>): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAssets(assetEntities: List<AssetEntity>): List<Long>

    @Query("DELETE FROM assets WHERE uid in (:ids)") suspend fun deleteAssets(ids: List<String>)

    @Transaction
    suspend fun insertAssetsWithHistoryAndSales(
        assets: List<AssetEntity>,
        priceHistories: List<PriceHistoryEntity>,
        sales: List<SalesEntity>,
    ) {
        insertAssets(assets)
        insertAllPriceHistory(priceHistories)
        insertAllSales(sales)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPriceHistory(history: List<PriceHistoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSales(sales: List<SalesEntity>)
}
