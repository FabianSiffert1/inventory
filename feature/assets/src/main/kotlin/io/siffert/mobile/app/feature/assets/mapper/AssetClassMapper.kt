package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.mapper

import android.content.Context
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.model.data.AssetClass

fun AssetClass.getDisplayName(context: Context): String {
    return when (this) {
        AssetClass.REAL_ASSET -> context.getString(R.string.feature_assets_class_real_asset)
        AssetClass.SECURITY -> context.getString(R.string.feature_assets_class_security)
        AssetClass.DIGITAL_ASSET -> context.getString(R.string.feature_assets_class_digital_asset)
    }
}
