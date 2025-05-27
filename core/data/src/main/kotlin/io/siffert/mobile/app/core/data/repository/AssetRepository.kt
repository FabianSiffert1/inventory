package io.siffert.mobile.app.core.data.repository

import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.Flow

interface AssetRepository {
    fun getAssetsFlow(): Flow<List<Asset>?>

    suspend fun getAssetsList(): List<Asset>?

    fun getAssetById(assetId: String): Flow<Asset?>

    /** Inserts [assets] in the db under the specified primary keys */
    suspend fun insertAssets(assets: List<Asset>): Result<Unit>

    suspend fun updateAssets(assets: List<Asset>): Result<Unit>

    suspend fun deleteAssets(assetIds: List<String>)
}
