package io.siffert.mobile.app.core.data.repository

import android.util.Log
import io.siffert.mobile.app.core.data.model.asEntity
import io.siffert.mobile.app.core.data.model.toPriceHistoryEntities
import io.siffert.mobile.app.core.data.model.toSalesEntity
import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.asExternalModel
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AssetRepositoryImpl(private val assetDao: AssetDao) : AssetRepository {
    override fun getAssetsFlow(): Flow<List<Asset>> =
        assetDao.getAssetsWithPriceHistoryAndSalesFlow().map { assetWithRelationsList ->
            assetWithRelationsList.map { it.asExternalModel() }
        }

    override suspend fun getAssetsList(): List<Asset> =
        assetDao.getAssetsWithPriceHistoryAndSales().map { it.asExternalModel() }

    override fun getAssetById(assetId: String): Flow<Asset?> =
        assetDao.getAssetWithPriceHistoryAndSalesFlow(assetId).map { it?.asExternalModel() }

    override suspend fun insertAssets(assets: List<Asset>): Result<Unit> {
        return try {
            Log.d("AssetRepository", "Inserting assets: $assets")
            val assetEntities = assets.map { it.asEntity() }
            val priceHistories = assets.flatMap { it.toPriceHistoryEntities() }
            val salesEntities = assets.mapNotNull { it.toSalesEntity() }

            assetDao.upsertAllAssetsWithHistoryAndSales(
                assets = assetEntities,
                priceHistories = priceHistories,
                sales = salesEntities,
            )

            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("AssetRepository", "Failed to upsert assets", e)
            Result.failure(e)
        }
    }

    override suspend fun updateAssets(assets: List<Asset>): Result<Unit> {
        return try {
            Log.d("AssetRepository", "Updating ${assets.size} assets")
            val assetEntities = assets.map { it.asEntity() }
            val updatedCount = assetDao.updateAssets(assetEntities)
            Log.d("AssetRepository", "Updated $updatedCount of ${assetEntities.size} assets")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("AssetRepository", "Failed to update assets", e)
            Result.failure(e)
        }
    }

    override suspend fun deleteAssets(assetIds: List<String>) {
        assetDao.deleteAssets(assetIds)
    }
}
