package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import io.siffert.mobile.app.core.database.model.AssetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao {
    @Query(
        value = """
        SELECT * FROM assets
        WHERE uid = :assetId
    """,
    )
    fun getAssetById(assetId: String): Flow<AssetEntity>

    @Query(value = "SELECT * FROM assets")
    fun getAssetsFlow(): Flow<List<AssetEntity>>

    @Query(value = "SELECT * FROM assets")
    suspend fun getAssetsList(): List<AssetEntity>

    @Query(
        value = """
        SELECT * FROM assets
        WHERE uid IN (:ids)
    """,
    )
    fun getAssetsByIds(ids: Set<String>): Flow<List<AssetEntity>>

    /**
     * Inserts [assetEntities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreAssets(assetEntities: List<AssetEntity>): List<Long>

    /**
     * Inserts or updates [entities] in the db under the specified primary keys
     */
    @Upsert
    suspend fun upsertAssets(entities: List<AssetEntity>)

    /**
     * Deletes rows in the db matching the specified [ids]
     */
    @Query(
        value = """
            DELETE FROM assets
            WHERE uid in (:ids)
        """,
    )
    suspend fun deleteAssets(ids: List<String>)

}