package io.siffert.mobile.app.core.data.repository

import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.dao.HistoricalPriceDao
import io.siffert.mobile.app.core.database.model.asExternalModel
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AssetRepositoryImpl(
    private val assetDao: AssetDao,
    private val historicalPricesDao: HistoricalPriceDao,
) : AssetRepository {
    override fun getAssetsFlow(): Flow<List<Asset>> =
        flow {
            assetDao.getAssetsFlow().collect { assetEntities ->
                val assets = assetEntities.map { entity ->
                    val historicalPrices =
                        historicalPricesDao.getHistoricalPricesForAsset(entity.uid)
                    entity.asExternalModel(historicalPrices)
                }
                emit(assets)
            }

        }

    override fun getAssetsList(): List<Asset> {
        TODO("Not yet implemented")
    }

    override fun getAssetById(assetId: String): Flow<Asset> {
        TODO("Not yet implemented")
    }

    override fun insertOrIgnoreAsset(assets: List<Asset>): List<Long> {
        TODO("Not yet implemented")
    }

    override fun upsertAssets(assets: List<Asset>) {
        TODO("Not yet implemented")
    }

    override fun deleteAssets(assetIds: List<String>) {
        TODO("Not yet implemented")
    }

}