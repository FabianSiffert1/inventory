package io.siffert.mobile.app.core.data.repository

import android.util.Log
import io.siffert.mobile.app.core.data.model.asEntity
import io.siffert.mobile.app.core.data.model.toPriceHistoryEntities
import io.siffert.mobile.app.core.data.model.toSalesEntity
import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.dao.PriceHistoryDao
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.dao.SalesDao
import io.siffert.mobile.app.core.database.io.siffert.mobile.app.core.database.model.asExternalModel
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AssetRepositoryImpl(
    private val assetDao: AssetDao,
    private val historicalPricesDao: PriceHistoryDao,
    private val salesDao: SalesDao,
) : AssetRepository {
    override fun getAssetsFlow(): Flow<List<Asset>> =
        assetDao.getAssetsWithPriceHistoryAndSalesFlow().map { assetWithRelationsList ->
            assetWithRelationsList.map { it.asExternalModel() }
        }

    override suspend fun getAssetsList(): List<Asset> =
        assetDao.getAssetsWithPriceHistoryAndSales().map { it.asExternalModel() }

    override fun getAssetById(assetId: String): Flow<Asset?> =
        assetDao.getAssetWithPriceHistoryAndSalesFlow(assetId).map { it?.asExternalModel() }

    override suspend fun insertOrIgnoreAsset(assets: List<Asset>): List<Long> {
        val assetEntities = assets.map { it.asEntity() }
        return assetDao.insertOrIgnoreAssets(assetEntities)
    }

    override suspend fun upsertAssets(assets: List<Asset>): Result<Unit> {
        return try {
            Log.d("AssetRepository", "Upserting assets: $assets")
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

    override suspend fun deleteAssets(assetIds: List<String>) {
        assetDao.deleteAssets(assetIds)
    }
}
