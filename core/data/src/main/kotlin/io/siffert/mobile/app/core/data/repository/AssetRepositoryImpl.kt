package io.siffert.mobile.app.core.data.repository

import io.siffert.mobile.app.core.data.model.asEntity
import io.siffert.mobile.app.core.data.model.toPriceHistoryEntities
import io.siffert.mobile.app.core.database.dao.AssetDao
import io.siffert.mobile.app.core.database.dao.PriceHistoryDao
import io.siffert.mobile.app.core.database.model.asExternalModel
import io.siffert.mobile.app.model.data.Asset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AssetRepositoryImpl(
    private val assetDao: AssetDao,
    private val historicalPricesDao: PriceHistoryDao,
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

    override suspend fun getAssetsList(): List<Asset> {
        val assetEntities = assetDao.getAssetsList()
        return assetEntities.map { assetEntity ->
            val historicalPrices =
                historicalPricesDao.getHistoricalPricesForAsset(assetEntity.uid)
            assetEntity.asExternalModel(historicalPrices)
        }
    }


    override fun getAssetById(assetId: String): Flow<Asset> = flow {
        val assetEntityFlow = assetDao.getAssetById(assetId)
        assetEntityFlow.map { assetEntity ->
            val historicalPrices =
                historicalPricesDao.getHistoricalPricesForAsset(assetEntityFlow.first().uid)
            assetEntity.asExternalModel(historicalPrices)
        }
    }

    override suspend fun insertOrIgnoreAsset(assets: List<Asset>): List<Long> {
        val assetEntities = assets.map { it.asEntity() }
        return assetDao.insertOrIgnoreAssets(assetEntities)
    }

    override suspend fun upsertAssets(assets: List<Asset>) {
        val assetEntities = assets.map { it.asEntity() }
        assetDao.upsertAssets(assetEntities)
        //todo: implement date sorting
        
        val historicalPriceEntities = assets.flatMap { it.toPriceHistoryEntities() }
        historicalPricesDao.insertAll(historicalPriceEntities)
    }

    override suspend fun deleteAssets(assetIds: List<String>) {
        assetDao.deleteAssets(assetIds)
    }

}