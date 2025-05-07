package io.siffert.mobile.app.core.data.repository

import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.Flow

interface AssetRepository {
    fun getAssetsFlow(): Flow<List<Asset>>

    suspend fun getAssetsList(): List<Asset>

    fun getAssetById(assetId: String): Flow<Asset>


    /**
     * Inserts [assets] into the db if they don't exist, and ignores those that do
     */
    fun insertOrIgnoreAsset(assets: List<Asset>): List<Long>

    /**
     * Inserts or updates [assets] in the db under the specified primary keys
     */
    fun upsertAssets(assets: List<Asset>)

    fun deleteAssets(assetIds: List<String>)
}