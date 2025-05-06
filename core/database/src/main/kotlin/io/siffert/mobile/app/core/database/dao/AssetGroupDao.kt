package io.siffert.mobile.app.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import io.siffert.mobile.app.core.database.model.AssetGroupEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AssetGroupDao {
    @Query(
        value = """
        SELECT * FROM asset_groups
        WHERE uid = :assetGroupId
    """,
    )
    fun getAssetGroupById(assetGroupId: String): Flow<AssetGroupEntity>

    @Query(
        value = """
        SELECT * FROM asset_groups
        WHERE name = :assetGroupName
    """,
    )
    fun getAssetGroupByName(assetGroupName: String): Flow<AssetGroupEntity>

    @Query(value = "SELECT * FROM asset_groups")
    fun getAllAssetGroupsFlow(): Flow<List<AssetGroupEntity>>

    @Query(value = "SELECT * FROM asset_groups")
    suspend fun getAssetGroupsList(): List<AssetGroupEntity>

    @Query(
        value = """
        SELECT * FROM asset_groups
        WHERE uid IN (:ids)
    """,
    )
    fun getAssetGroupsByIds(ids: Set<String>): Flow<List<AssetGroupEntity>>

    /**
     * Inserts [assetGroups] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreAssetGroups(assetGroups: List<AssetGroupEntity>): List<Long>

    /**
     * Inserts or updates [assetGroups] in the db under the specified primary keys
     */
    @Upsert
    suspend fun upsertAssetGroups(assetGroups: List<AssetGroupEntity>)

    /**
     * Deletes rows in the db matching the specified [ids]
     */
    @Query(
        value = """
            DELETE FROM asset_groups
            WHERE uid in (:ids)
        """,
    )
    suspend fun deleteAssetGroups(ids: List<String>)

}