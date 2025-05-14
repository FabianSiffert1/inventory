package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.siffert.mobile.app.feature.assets.R
import io.siffert.mobile.app.inventory.core.designsystem.icons.DigitalAsset
import io.siffert.mobile.app.inventory.core.designsystem.icons.RealAsset
import io.siffert.mobile.app.inventory.core.designsystem.icons.Security
import io.siffert.mobile.app.inventory.core.designsystem.theme.Cozy
import io.siffert.mobile.app.model.data.AssetClass

@Composable
internal fun AssetClassIcon(assetClass: AssetClass, modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.padding(4.dp),
        imageVector =
            when (assetClass) {
                AssetClass.REAL_ASSET -> Cozy.icon.RealAsset
                AssetClass.SECURITY -> Cozy.icon.Security
                AssetClass.DIGITAL_ASSET -> Cozy.icon.DigitalAsset
            },
        contentDescription =
            when (assetClass) {
                AssetClass.REAL_ASSET ->
                    stringResource(id = R.string.feature_assets_class_real_asset)
                AssetClass.SECURITY -> stringResource(id = R.string.feature_assets_class_security)
                AssetClass.DIGITAL_ASSET ->
                    stringResource(id = R.string.feature_assets_class_digital_asset)
            },
    )
}
