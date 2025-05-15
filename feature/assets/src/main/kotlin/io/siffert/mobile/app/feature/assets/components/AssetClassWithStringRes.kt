package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.annotation.StringRes
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.model.data.AssetClass

enum class AssetClassWithStringRes(@StringRes val nameResource: Int, val assetClass: AssetClass) {
    REAL_ASSET(R.string.feature_assets_class_real_asset, AssetClass.REAL_ASSET),
    SECURITY(R.string.feature_assets_class_security, AssetClass.SECURITY),
    DIGITAL_ASSET(R.string.feature_assets_class_digital_asset, AssetClass.DIGITAL_ASSET);

    companion object {
        fun from(assetClass: AssetClass): AssetClassWithStringRes =
            entries.first { it.assetClass == assetClass }
    }
}
