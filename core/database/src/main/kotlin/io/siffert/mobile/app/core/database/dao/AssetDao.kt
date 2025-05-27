package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.AssetEntityWithPricesAndSales
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.SalesEntity
import io.siffert.mobile.app.core.database.model.AssetEntity
import io.siffert.mobile.app.core.database.model.PriceHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {

    @Transaction
    @Query("SELECT * FROM assets WHERE uid = :assetId")
    suspend fun getAssetWithPriceHistoryAndSales(assetId: String): AssetEntityWithPricesAndSales?

    @Transaction
    @Query("SELECT * FROM assets WHERE uid = :assetId")
    fun getAssetWithPriceHistoryAndSalesFlow(assetId: String): Flow<AssetEntityWithPricesAndSales?>

    @Transaction
    @Query("SELECT * FROM assets")
    fun getAssetsWithPriceHistoryAndSalesFlow(): Flow<List<AssetEntityWithPricesAndSales>>

    @Transaction
    @Query("SELECT * FROM assets")
    suspend fun getAssetsWithPriceHistoryAndSales(): List<AssetEntityWithPricesAndSales>

    @Query("SELECT * FROM assets WHERE uid IN (:ids)")
    fun getAssetsWithPriceHistoryAndSalesByIds(
        ids: Set<String>
    ): Flow<List<AssetEntityWithPricesAndSales?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertAsset(asset: AssetEntity)

    @Insert suspend fun insertPriceHistory(history: PriceHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreAssets(assetEntities: List<AssetEntity>): List<Long>

    @Upsert suspend fun upsertAssets(assets: List<AssetEntity>)

    @Query("DELETE FROM assets WHERE uid in (:ids)") suspend fun deleteAssets(ids: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPriceHistory(history: List<PriceHistoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSales(sales: List<SalesEntity>)

    @Transaction
    suspend fun upsertAllAssetsWithHistoryAndSales(
        assets: List<AssetEntity>,
        priceHistories: List<PriceHistoryEntity>,
        sales: List<SalesEntity>,
    ) {
        upsertAssets(assets)
        insertAllPriceHistory(priceHistories)
        insertAllSales(sales)
    }
}
