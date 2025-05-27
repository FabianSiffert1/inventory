package io.siffert.mobile.app.core.domain

import android.util.Log
import io.siffert.mobile.app.core.data.repository.AssetRepository
import io.siffert.mobile.app.model.data.Asset

class UpdateAssetUseCase(private val assetRepository: AssetRepository) {
    suspend fun updateAsset(asset: Asset): Result<Unit> {
        Log.d("UpdateAssetUseCase", "Attempting to update asset with ID: ${asset.id}, data: $asset")

        return assetRepository
            .updateAssets(listOf(asset))
            .onFailure { throwable ->
                Log.e("UpdateAssetUseCase", "Asset update failed: ${throwable.message}", throwable)
                return Result.failure(
                    IllegalArgumentException("Asset update failed: ${throwable.message}")
                )
            }
            .onSuccess {
                Log.d("UpdateAssetUseCase", "Asset update successful for ID: ${asset.id}")
            }
    }
}
