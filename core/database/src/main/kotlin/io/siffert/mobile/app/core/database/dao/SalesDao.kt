package io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.SalesEntity
import io.siffert.mobile.app.core.database.model.AssetWithPriceHistoryAndSales

@Dao
interface SalesDao {

    @Insert suspend fun insert(salesEntity: SalesEntity)

    @Transaction @Insert suspend fun insertAll(salesEntities: List<SalesEntity>)

    @Update suspend fun update(salesEntity: SalesEntity)

    @Delete suspend fun delete(salesEntity: SalesEntity)

    @Query("SELECT * FROM sales WHERE assetId = :assetId")
    suspend fun getSaleByAssetId(assetId: String): SalesEntity?

    @Query("SELECT * FROM sales") suspend fun getAllSales(): List<SalesEntity>

    @Transaction
    @Query("SELECT * FROM assets WHERE uid = :assetId")
    suspend fun getAssetWithSaleAndPriceHistory(assetId: String): AssetWithPriceHistoryAndSales
}
